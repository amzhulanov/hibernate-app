package com.flamexander.hibernate.hw3.one_to_many;

import com.flamexander.hibernate.PrepareDataApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/hw3/hibernate.cfg.OneToMany.xml")
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customer = session.get(Customer.class, 3L);
            System.out.println(customer);
            System.out.println("Bought what customer_id=3: ");
            for (Item i : customer.getItems()) {
                System.out.println(i.getTitle());
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
