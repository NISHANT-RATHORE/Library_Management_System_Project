package org.example.library_management_system.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.enums.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Book> findBookByFilter(String bookTitle, BookType bookType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        List<Predicate> predicates = new ArrayList<>(); //filters
        if(bookTitle != null && !bookTitle.isEmpty()){
            Predicate titlePrdicate = criteriaBuilder.like(bookRoot.get("bookTitle"),"%"+bookTitle+"%");
            predicates.add(titlePrdicate);
        }
        if(bookType!=null){
            Predicate typePredicate = criteriaBuilder.equal(bookRoot.get("bookType"),bookType);
            predicates.add(typePredicate);
        }
        Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        criteriaQuery.select(bookRoot).where(finalPredicate);
//        criteriaQuery.select(bookRoot).where(predicates.toArray(new Predicate[0]));
//        Predicate amount = criteriaBuilder.equal(bookRoot.get("securityAmount"),100);
//        Predicate semifinal  = criteriaBuilder.and(finalPredicate,amount);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}