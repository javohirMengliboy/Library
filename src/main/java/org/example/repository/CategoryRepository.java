package org.example.repository;

import lombok.Setter;
import org.example.dto.CategoryDTO;
import org.example.entity.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@Setter
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<CategoryDTO> getCategoryList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.CategoryDTO(c.id , c.name) from CategoryEntity as c");
        return (List<CategoryDTO>) query.getResultList();
    }

    public CategoryEntity getCategoryByName(String categoryName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CategoryEntity where name = :name");
        query.setParameter("name", categoryName);
        return  (CategoryEntity) query.getSingleResult();
    }

    public boolean addCategory(CategoryEntity categoryEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(categoryEntity);
        transaction.commit();
        session.close();
        return true;
    }
}
