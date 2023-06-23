package org.example.repository;

import lombok.Setter;
import org.example.dto.ProfileDTO;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Setter
public class ProfileRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public ProfileDTO getProfileByLogin(String login) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select new org.example.dto.ProfileDTO(p.id, p.name, p.surname, p.login, p.password, p.phone, p.status, p.role, p.createdDate) from ProfileEntity as p where p.login = :login");
            query.setParameter("login", login);
            ProfileDTO profileDTO = (ProfileDTO) query.getSingleResult();
            System.out.println(profileDTO);
            return null;
        } catch (NoResultException  e) {
            return null;
        }finally {
            session.close();
        }
    }

    public ProfileDTO getProfileByLogin2(String login) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select p.id, p.name, p.surname, p.login, p.password, p.phone, p.status, p.role, p.createdDate from ProfileEntity as p where p.login = :login");
            query.setParameter("login", login);
            Object[] objects = (Object[]) query.getSingleResult();
            ProfileDTO profileDTO = new ProfileDTO((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4], (String) objects[5], (ProfileStatus) objects[6], (ProfileRole) objects[7], (LocalDateTime) objects[8]);
            return profileDTO;
        } catch (NoResultException  e) {
            return null;
        }finally {
            session.close();
        }
    }

    public boolean saveStudent(ProfileEntity profileEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Integer result = (Integer) session.save(profileEntity);
            transaction.commit();
            return true;
        } catch (PropertyValueException e) {
            return false;
        }
    }

    public ProfileEntity getProfileByPhone(String phone) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(" from ProfileEntity as p" +
                    " where p.phone = :phone");
            query.setParameter("phone", phone);
            return (ProfileEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ProfileEntity getProfileById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProfileEntity profileEntity = session.find(ProfileEntity.class,id);
        transaction.commit();
        session.close();
        return profileEntity;
    }

    public List<ProfileDTO> getProfileList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.ProfileDTO(id,name, surname, login, password, phone, status, role, createdDate) from ProfileEntity");
        List<ProfileDTO> profileDTOList = query.getResultList();
        session.close();
        return profileDTOList;
    }

    public List<ProfileDTO> getStudentList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.ProfileDTO(id,name, surname, login, password, phone, status, role, createdDate) from ProfileEntity where role = 'STUDENT'");
        List<ProfileDTO> profileDTOList = query.getResultList();
        session.close();
        return profileDTOList;
    }

    public ProfileEntity searchStudent(String value) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ProfileEntity as p where p.name = :value or p.surname = :value" +
                " or p.login = :value or p.phone = :value and p.role = 'STUDENT'");
        query.setParameter("value", value);
        ProfileEntity profileEntity = (ProfileEntity) query.getSingleResult();
        session.close();
        return profileEntity;
    }

    public ProfileEntity searchProfile(String value) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ProfileEntity as p where p.name = :value or p.surname = :value" +
                " or p.login = :value or p.phone = :value");
        query.setParameter("value", value);
        ProfileEntity profileEntity = (ProfileEntity) query.getSingleResult();
        session.close();
        return profileEntity;
    }

    public boolean blockingStudent(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update ProfileEntity as p set p.status = 'BLOCK' where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public boolean activateStudent(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update ProfileEntity as p set p.status = 'ACTIVE' where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public List<ProfileEntity> studentByBook(int id) {
        return null;
    }

    public boolean changeStatus(int id, ProfileStatus status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update ProfileEntity as p set p.status = :status where p.id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
