package com.flamexander.hibernate.hw3.many_to_many;

import com.flamexander.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManyToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/hw3/hibernate.cfg.ManyToMany.xml")
                .buildSessionFactory();

        Session session = null;
        try {

            session = factory.getCurrentSession();
            session.beginTransaction();
            Products product = session.get(Products.class, 1L);
//Кто купил молоко
            System.out.println(product);
            System.out.println("Who bought the milk: ");
            for (Customers b : product.getCustomers()) {
                System.out.println(b.getName()+" "+b.getLastname());
            }
//Что купил клиент
            Customers customers=session.get(Customers.class,1L);

                    //("bread",20f);
            System.out.println("Bought what customer_id=1");
            for (Products p : customers.getProducts()) {

                System.out.println(p.toString());
            }
            //readerWithId1.getBooks().add(bookWithId3);
            product = session.get(Products.class, 3L);
            customers=session.get(Customers.class,3L);
            product.getCustomers().add(customers);
            System.out.println("Bought what customer_id=3");
            for (Products p : customers.getProducts()) {

                System.out.println(p.toString());
            }


            List<Products> products = session.createQuery("SELECT title, cost FROM Item products ORDER BY cost DESC").getResultList();
            System.out.println(products.toString());

            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
