package org.example.library_management_system.Service;

import jakarta.transaction.Transactional;
import org.example.library_management_system.DTO.TransactionRequest;
import org.example.library_management_system.Exceptions.TransactionException;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Model.Transactions;
import org.example.library_management_system.Model.User;
import org.example.library_management_system.Repository.TransactionRepository;
import org.example.library_management_system.enums.TransactionStatus;
import org.example.library_management_system.enums.UserStatus;
import org.example.library_management_system.enums.UserType;
import org.hibernate.validator.internal.constraintvalidators.bv.money.CurrencyValidatorForMonetaryAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${book.maximum.validity}")
    int bookValidDays;

    @Value("${book.fine.per.day}")
    int bookFinePerDay;

    public Transactions issueBook(TransactionRequest request) throws TransactionException{
        User user = fetchUser(request);
        if(user.getUserStatus() == UserStatus.BLocked){
            throw new TransactionException("User is blocked can't issue book");
        }
        Book book = fetchBook(request);
        if(book.getUser()!=null){
            throw new TransactionException("Book cannot be issued");
        }
        return issuebook(user,book);
    }

    @Transactional
    protected Transactions issuebook(User user, Book book){
        Transactions transactions = Transactions.builder()
                .book(book)
                .user(user)
                .transactionId(UUID.randomUUID().toString().substring(0,30))
                .transactionStatus(TransactionStatus.Issued)
                .settlementAmount(-book.getSecurityAmount())
                .build();
        transactions = transactionRepository.save(transactions);
        book.setUser(user);
        bookService.updateBookMetaData(book);
        return transactions;
    }

    private User fetchUser(TransactionRequest request) throws TransactionException{
        User user = userService.fetchUserByEmail(request.getUserEmail());
        if(user == null){
            throw new TransactionException("User is not exist");
        }
        if(user.getUserType() != UserType.Student){
            throw new TransactionException("UserType must be Student");
        }
        return user;
    }

    private Book fetchBook(TransactionRequest request)throws TransactionException{
        Book book = bookService.getBookByBookNo(request.getBookNo());

        if(book == null){
            throw new TransactionException("Book doen't exist");
        }
        return book;
    }

    public Integer returnBook(TransactionRequest request) throws TransactionException {
        User user = fetchUser(request);
        Book book = fetchBook(request);
        if(book.getUser()!=user){
            throw new TransactionException("Book is issued to some other user");
        }
        Transactions transactions = transactionRepository.findByUserEmailAndBookBookNo(request.getUserEmail(),request.getBookNo());
        return returnbook(transactions,book);
    }

    @Transactional
    protected Integer returnbook(Transactions transactions,Book book){
        long issuedDateinTime = transactions.getCreateOn().getTime();
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - issuedDateinTime;
        long days = TimeUnit.MILLISECONDS.toDays(timeDifference);

        int amount = 0;
        if(amount>bookValidDays){
            int fine =(int) (days - bookValidDays) * bookFinePerDay;
            amount = fine - Math.abs(transactions.getSettlementAmount());
            transactions.setSettlementAmount(-fine);
            transactions.setTransactionStatus(TransactionStatus.Fined);
        }else{
            transactions.setTransactionStatus(TransactionStatus.Returned);
            amount = transactions.getSettlementAmount();
            transactions.setSettlementAmount(0);
        }

        transactionRepository.save(transactions);

        book.setUser(null);
        bookService.updateBookMetaData(book);
        return amount;
    }
}
