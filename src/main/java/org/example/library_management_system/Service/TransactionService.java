package org.example.library_management_system.Service;

import org.example.library_management_system.DTO.TransactionRequest;
import org.example.library_management_system.Exceptions.TransactionException;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Model.Transactions;
import org.example.library_management_system.Model.User;
import org.example.library_management_system.Repository.TransactionRepository;
import org.example.library_management_system.enums.TransactionStatus;
import org.example.library_management_system.enums.UserStatus;
import org.example.library_management_system.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    public Transactions issueBook(TransactionRequest request) {
        User user = fetchUser(request);
        Book book = fetchBook(request);
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
    private User fetchUser(TransactionRequest request){
        User user = userService.fetchUserByEmail(request.getUserEmail());
        if(user == null){
            throw new TransactionException("User is not exist");
        }
        if(user.getUserType() != UserType.Student){
            throw new TransactionException("UserType must be Student");
        }
        if(user.getUserStatus() == UserStatus.BLocked){
            throw new TransactionException("User is blocked can't issue book");
        }
        return user;
    }

    private Book fetchBook(TransactionRequest request){
        Book book = bookService.getBookByBookNo(request.getBookNo());

        if(book == null){
            throw new TransactionException("Book doen't exist");
        }
        if(book.getUser()!=null){
            throw new TransactionException("Book cannot be issued");
        }
        return book;
    }
}
