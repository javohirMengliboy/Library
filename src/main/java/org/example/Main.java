package org.example;

import lombok.Setter;
import org.example.config.SpringConfig;
import org.example.controller.MainController;
import org.example.dto.BookDTO;
import org.example.dto.CategoryDTO;
import org.example.entity.BookEntity;
import org.example.entity.CategoryEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TakenBookEntity;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.example.enums.TakenBookStatus;
import org.example.mapper.BestBookMapper;
import org.example.repository.TakenBookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Setter
public class Main {


    @Autowired
    private static TakenBookRepository takenBookRepository;
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        MainController mainController = (MainController) context.getBean("mainController");
//        mainController.start();

//        LocalDateTime localDateTime1 = LocalDateTime.now();
//        System.out.println(localDateTime1);
//        LocalDateTime localDateTime2 = null;
//        System.out.println(localDateTime2);

//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//        List<BestBookMapper> list = takenBookRepository.getBestBookList();
//        list.forEach(System.out::println);



//        Transaction tr1 = session.beginTransaction();
//
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setName("hikoya");
//        categoryEntity.setCreatedDate(LocalDateTime.now());
//        categoryEntity.setVisible(true);
//        session.save(categoryEntity);
//        tr1.commit();
//
//        Transaction tr2 = session.beginTransaction();
//        CategoryEntity categoryEntity2 = new CategoryEntity();
//        categoryEntity2.setName("history");
//        categoryEntity2.setCreatedDate(LocalDateTime.now());
//        categoryEntity2.setVisible(true);
//        session.save(categoryEntity2);
//        tr2.commit();
//
//        Transaction tr3 = session.beginTransaction();
//        CategoryEntity categoryEntity3 = new CategoryEntity();
//        categoryEntity3.setName("detiktiv");
//        categoryEntity3.setCreatedDate(LocalDateTime.now());
//        categoryEntity3.setVisible(true);
//        session.save(categoryEntity3);
//        tr3.commit();
//
//
//        Transaction tr4 = session.beginTransaction();
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setTitle("k1");
//        bookEntity.setAuthor("A1");
//        bookEntity.setCategory(categoryEntity2);
//        bookEntity.setPublishedDate(LocalDateTime.of(2022,2,20,4,5));
//        bookEntity.setVisible(true);
//        bookEntity.setCreatedDate(LocalDateTime.now());
//        session.save(bookEntity);
//        tr4.commit();
//
//        Transaction tr5 = session.beginTransaction();
//
//        BookEntity bookEntity2 = new BookEntity();
//        bookEntity2.setTitle("k2");
//        bookEntity2.setAuthor("A1");
//        bookEntity2.setCategory(categoryEntity2);
//        bookEntity2.setPublishedDate(LocalDateTime.of(2022,2,20,4,5));
//        bookEntity2.setVisible(true);
//        bookEntity2.setCreatedDate(LocalDateTime.now());
//        session.save(bookEntity2);
//        tr5.commit();
//
//        Transaction tr6 = session.beginTransaction();
//
//        BookEntity bookEntity3 = new BookEntity();
//        bookEntity3.setTitle("k3");
//        bookEntity3.setAuthor("A2");
//        bookEntity3.setCategory(categoryEntity3);
//        bookEntity3.setPublishedDate(LocalDateTime.of(2022,2,20,4,5));
//        bookEntity3.setVisible(true);
//        bookEntity3.setCreatedDate(LocalDateTime.now());
//        session.save(bookEntity3);
//
//        tr6.commit();
//
//        Transaction tr7 = session.beginTransaction();
//
//        BookEntity bookEntity4 = new BookEntity();
//        bookEntity4.setTitle("k4");
//        bookEntity4.setAuthor("A3");
//        bookEntity4.setCategory(categoryEntity);
//        bookEntity4.setPublishedDate(LocalDateTime.of(2022,2,20,4,5));
//        bookEntity4.setVisible(true);
//        bookEntity4.setCreatedDate(LocalDateTime.now());
//        session.save(bookEntity4);
//        tr7.commit();
//
//        Transaction tr8 = session.beginTransaction();
//
//
//        ProfileEntity profileEntity = new ProfileEntity();
//        profileEntity.setName("Ali");
//        profileEntity.setSurname("Aliyev");
//        profileEntity.setLogin("ali1");
//        profileEntity.setPassword("Aliyev11");
//        profileEntity.setPhone("+998996663322");
//        profileEntity.setStatus(ProfileStatus.ACTIVE);
//        profileEntity.setRole(ProfileRole.STUDENT);
//        profileEntity.setCreatedDate(LocalDateTime.now());
//        session.save(profileEntity);
//
//        tr8.commit();
//
//        Transaction tr9 = session.beginTransaction();
//
//
//        ProfileEntity profileEntity2 = new ProfileEntity();
//        profileEntity2.setName("Vali");
//        profileEntity2.setSurname("Valiyev");
//        profileEntity2.setLogin("vali1");
//        profileEntity2.setPassword("Valiyev11");
//        profileEntity2.setPhone("+998996663311");
//        profileEntity2.setStatus(ProfileStatus.ACTIVE);
//        profileEntity2.setRole(ProfileRole.STUDENT);
//        profileEntity2.setCreatedDate(LocalDateTime.now());
//        session.save(profileEntity2);
//        tr9.commit();
//
//        Transaction tr10 = session.beginTransaction();
//        ProfileEntity profileEntity3 = new ProfileEntity();
//        profileEntity3.setName("Hasan");
//        profileEntity3.setSurname("Hasanov");
//        profileEntity3.setLogin("hasan1");
//        profileEntity3.setPassword("Hasanov11");
//        profileEntity3.setPhone("+998996663300");
//        profileEntity3.setStatus(ProfileStatus.ACTIVE);
//        profileEntity3.setRole(ProfileRole.STUDENT);
//        profileEntity3.setCreatedDate(LocalDateTime.now());
//        session.save(profileEntity3);
//        tr10.commit();
//
//        Transaction tr11 = session.beginTransaction();
//
//
//        ProfileEntity profileEntity4 = new ProfileEntity();
//        profileEntity4.setName("Husan");
//        profileEntity4.setSurname("Husanov");
//        profileEntity4.setLogin("husan1");
//        profileEntity4.setPassword("Husanov11");
//        profileEntity4.setPhone("+998996663399");
//        profileEntity4.setStatus(ProfileStatus.ACTIVE);
//        profileEntity4.setRole(ProfileRole.STUDENT);
//        profileEntity4.setCreatedDate(LocalDateTime.now());
//        session.save(profileEntity4);
//        tr11.commit();
//
//        Transaction t1 = session.beginTransaction();
//
//
//        ProfileEntity profileEntity5 = new ProfileEntity();
//        profileEntity5.setName("Obid");
//        profileEntity5.setSurname("Obidov");
//        profileEntity5.setLogin("obid1");
//        profileEntity5.setPassword("Obidov11");
//        profileEntity5.setPhone("+998996663388");
//        profileEntity5.setStatus(ProfileStatus.ACTIVE);
//        profileEntity5.setRole(ProfileRole.STUDENT);
//        profileEntity5.setCreatedDate(LocalDateTime.now());
//        session.save(profileEntity5);
//        t1.commit();
//
//        Transaction t2 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity = new TakenBookEntity();
//        takenBookEntity.setProfileEntity(profileEntity);
//        takenBookEntity.setBookEntity(bookEntity);
//        takenBookEntity.setTakenDate(LocalDateTime.now());
//        takenBookEntity.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity.setNote("good");
//        session.save(takenBookEntity);
//        t2.commit();
//
//        Transaction t3 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity2 = new TakenBookEntity();
//        takenBookEntity2.setProfileEntity(profileEntity2);
//        takenBookEntity2.setBookEntity(bookEntity);
//        takenBookEntity2.setTakenDate(LocalDateTime.now());
//        takenBookEntity2.setReturnDate(takenBookEntity.getTakenDate().plusDays(6));
//        takenBookEntity2.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity2.setNote("good");
//        session.save(takenBookEntity2);
//
//        t3.commit();
//
//        Transaction t5 = session.beginTransaction();
//
//        TakenBookEntity takenBookEntity3 = new TakenBookEntity();
//        takenBookEntity3.setProfileEntity(profileEntity3);
//        takenBookEntity3.setBookEntity(bookEntity);
//        takenBookEntity3.setTakenDate(LocalDateTime.now());
//        takenBookEntity3.setReturnDate(takenBookEntity.getTakenDate().plusDays(8));
//        takenBookEntity3.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity3.setNote("good");
//        session.save(takenBookEntity3);
//        t5.commit();
//
//        Transaction t4 = session.beginTransaction();
//
//        TakenBookEntity takenBookEntity4 = new TakenBookEntity();
//        takenBookEntity4.setProfileEntity(profileEntity4);
//        takenBookEntity4.setBookEntity(bookEntity2);
//        takenBookEntity4.setTakenDate(LocalDateTime.now());
//        takenBookEntity4.setReturnDate(takenBookEntity.getTakenDate().plusDays(3));
//        takenBookEntity4.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity4.setNote("good");
//        session.save(takenBookEntity4);
//        t4.commit();
//        Transaction t6 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity5 = new TakenBookEntity();
//        takenBookEntity5.setProfileEntity(profileEntity5);
//        takenBookEntity5.setBookEntity(bookEntity2);
//        takenBookEntity5.setTakenDate(LocalDateTime.now());
//        takenBookEntity5.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity5.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity5.setNote("good");
//        session.save(takenBookEntity5);
//        t6.commit();
//
//        Transaction t7 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity6 = new TakenBookEntity();
//        takenBookEntity6.setProfileEntity(profileEntity2);
//        takenBookEntity6.setBookEntity(bookEntity3);
//        takenBookEntity6.setTakenDate(LocalDateTime.now());
//        takenBookEntity6.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity6.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity6.setNote("good");
//        session.save(takenBookEntity6);
//        t7.commit();
//
//        Transaction t8 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity7 = new TakenBookEntity();
//        takenBookEntity7.setProfileEntity(profileEntity);
//        takenBookEntity7.setBookEntity(bookEntity4);
//        takenBookEntity7.setTakenDate(LocalDateTime.now());
//        takenBookEntity7.setReturnDate(takenBookEntity.getTakenDate().plusDays(7));
//        takenBookEntity7.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity7.setNote("good");
//        session.save(takenBookEntity7);
//        t8.commit();
//
//        Transaction t9 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity8 = new TakenBookEntity();
//        takenBookEntity8.setProfileEntity(profileEntity5);
//        takenBookEntity8.setBookEntity(bookEntity);
//        takenBookEntity8.setTakenDate(LocalDateTime.now());
//        takenBookEntity8.setReturnDate(takenBookEntity.getTakenDate().plusDays(9));
//        takenBookEntity8.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity.setNote("good");
//        session.save(takenBookEntity8);
//        t9.commit();
//
//        Transaction t10 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity9 = new TakenBookEntity();
//        takenBookEntity9.setProfileEntity(profileEntity3);
//        takenBookEntity9.setBookEntity(bookEntity2);
//        takenBookEntity9.setTakenDate(LocalDateTime.now());
//        takenBookEntity9.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity9.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity9.setNote("good");
//        session.save(takenBookEntity9);
//        t10.commit();
//
//        Transaction t14 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity10 = new TakenBookEntity();
//        takenBookEntity10.setProfileEntity(profileEntity2);
//        takenBookEntity10.setBookEntity(bookEntity3);
//        takenBookEntity10.setTakenDate(LocalDateTime.now());
//        takenBookEntity10.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity10.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity10.setNote("good");
//        session.save(takenBookEntity10);
//
//        t14.commit();
//
//        Transaction t15 = session.beginTransaction();
//
//
//        TakenBookEntity takenBookEntity11 = new TakenBookEntity();
//        takenBookEntity11.setProfileEntity(profileEntity4);
//        takenBookEntity11.setBookEntity(bookEntity);
//        takenBookEntity11.setTakenDate(LocalDateTime.now());
//        takenBookEntity11.setReturnDate(takenBookEntity.getTakenDate().plusDays(5));
//        takenBookEntity11.setStatus(TakenBookStatus.TOOK);
//        takenBookEntity11.setNote("good");
//        session.save(takenBookEntity11);
//
//        t15.commit();
//
//        session.close();
//        sessionFactory.close();
    }
}