package org.example.repository;

import lombok.Setter;
import org.example.dto.BookDTO;
import org.example.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.List;

@Repository
@Setter
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public List<BookDTO> getBookList(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDTO(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where b.visible != false");
        List<BookDTO> bookEntityList = query.getResultList();
        session.close();
        return bookEntityList;
    }

    public List<BookDTO> searchBook(String value) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDTO(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where b.title = :v or b.author = :v or category.name = :v and b.visible != false");
        query.setParameter("v", value);
        List<BookDTO> bookEntityList = query.getResultList();
        session.close();
        return bookEntityList;
    }


    public List<BookDTO> getBookListByCategory(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDTO(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where category.id = :id and b.visible != false");
        query.setParameter("id", id);
        List<BookDTO> bookDTOList = query.getResultList();
        session.close();
        return bookDTOList;
    }

    public BookDTO getBookDTOById(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDTO(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where b.id = :id and b.visible != false ");
        query.setParameter("id", id);
        BookDTO bookDTO = (BookDTO) query.getSingleResult();
        System.out.println(bookDTO);
        return bookDTO;
    }
    public BookEntity getBookEntityById(int id) {
        Session session = sessionFactory.openSession();
        BookEntity bookEntity = session.find(BookEntity.class, id);
        System.out.println(bookEntity);
        return bookEntity;
    }


    public boolean addBook(BookEntity bookEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookEntity);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean removeBook(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update BookEntity as b set visible = false where id = :id");
        query.setParameter("id", id);
        transaction.commit();
        session.close();
        return true;
    }
}
