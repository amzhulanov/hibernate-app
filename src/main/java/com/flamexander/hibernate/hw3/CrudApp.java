package com.flamexander.hibernate.hw3;

import com.flamexander.hibernate.PrepareDataApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudApp {
    public static void main(String[] args) throws Exception {
        PrepareDataApp.forcePrepareData();

        Long maxId = null;

        SessionFactory factory = new Configuration()
                .configure("configs/hw3/hibernate.cfg.crud.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            System.out.println("============\n== CREATE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products newProducts=new Products("tea",23.4f);

            System.out.println("Before save: " + newProducts);
            session.save(newProducts);
            System.out.println("After save: " + newProducts);
            session.getTransaction().commit();
            System.out.println("After save and commit: " + newProducts);

            System.out.println("============\n=== READ ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products productsFromDb = session.get(Products.class, 1L);
            session.get(Products.class, 1L); // Повторное вычитывание объекта (будет вытащен из кеша)
            System.out.println(productsFromDb);
            session.getTransaction().commit();

            System.out.println("============\n=== READ IN ANOTHER SESSION ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products productsFromDb2 = session.get(Products.class, 1L);
            System.out.println(productsFromDb2);
            session.getTransaction().commit();

            System.out.println("============\n== UPDATE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            maxId = session.createQuery("SELECT MAX(s.id) FROM Item s", Long.class).getSingleResult();
            Products productsForUpdate = session.createQuery("SELECT s FROM Item s WHERE s.id = :id", Products.class)
                    .setParameter("id", maxId)
                    .getSingleResult();
            System.out.println("Loaded item with max(id): " + productsForUpdate);
            productsForUpdate.setPrice(productsForUpdate.getCost() + 100);
            System.out.println("Modified item: " + productsForUpdate);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Products productsAfterUpdate = session.get(Products.class, productsForUpdate.getId());
            System.out.println("Loaded item after update: " + productsAfterUpdate);
            session.getTransaction().commit();

            System.out.println("============\n== DELETE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            maxId = session.createQuery("SELECT MAX(s.id) FROM Item s", Long.class).getSingleResult();
            session.delete(session.get(Products.class, maxId));
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Products removedProducts = session.get(Products.class, maxId);
            System.out.println("Loaded item after remove: " + removedProducts);
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
