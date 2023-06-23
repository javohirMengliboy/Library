package org.example.repository;

import lombok.Setter;
import org.example.dto.BookDTO;
import org.example.entity.TakenBookEntity;
import org.example.mapper.BestBookMapper;
import org.example.mapper.BookHistoryMapper;
import org.example.mapper.BookMapper;
import org.example.mapper.TakenBookMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Setter
public class TakenBookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public boolean saveTakenBook(TakenBookEntity takenBookEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(takenBookEntity);
        transaction.commit();
        session.close();
        return true;
    }

    public List<BookMapper> getBookListByProfileId(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.mapper.BookMapper(b.id, b.title, b.author, b.category.name," +
                " t.createdDate, b.availableDate) from TakenBookEntity as t inner join t.bookEntity as b inner join t.profileEntity as p where p.id = :id");
        query.setParameter("id", id);
        List<BookMapper> bookMapperList = query.getResultList();
        session.close();
        return bookMapperList;
    }

    public List<Object[]> getTakenBookHistory(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select b.id, b.title, b.author," +
                " b.category.name, t.createdDate from TakenBookEntity as t inner join t.bookEntity as b " +
                "inner join t.profileEntity as p where p.id = :id");
        query.setParameter("id", id);
        List<Object[]> bookMapperList = query.getResultList();
        session.close();
        return bookMapperList;
    }

    public List<Object[]> getTakenBookMapperList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select b.id, b.title," +
                " b.author, b.category.name, t.createdDate, b.availableDate, p.name, p.surname, p.phone " +
                "from TakenBookEntity as t inner join t.bookEntity as b inner join t.profileEntity as p" +
                " where b.availableDate != null");
        List<Object[]> takenBookMapper = query.getResultList();
        session.close();
        return takenBookMapper;
    }

    public List<Object[]> getBookHistory(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select t.createdDate," +
                " p.name, p.surname, p.phone, t.note from TakenBookEntity as t inner join t.bookEntity as b " +
                "inner join t.profileEntity as p where b.id = :id");
        query.setParameter("id", id);
        List<Object[]> bookHistoryMapper = query.getResultList();
        session.close();
        return bookHistoryMapper;
    }

    public List<Object[]> getBestBookList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select b.id, b.title, b.author, b.category.name, count(tk) from TakenBookEntity as tk inner join tk.bookEntity as b " +
                "group by tk.bookEntity");
        List<Object[]> bestBookMapperList = query.getResultList();
        session.close();
        bestBookMapperList.forEach(System.out::println);
        return null;
    }
}
