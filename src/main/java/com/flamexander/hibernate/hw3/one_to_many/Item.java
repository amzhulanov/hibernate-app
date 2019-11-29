package com.flamexander.hibernate.hw3.one_to_many;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items_hw3")
public class Item implements Serializable {

    private static final long serialVersionUID = -2750973356670718107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer){this.customer = customer;}

    public Item() {
    }

    public Item(String title, int cost,Customer customer) {
        this.title = title;
        this.cost = cost;
        this.customer=customer;
    }

    @Override
    public String toString() {
        return String.format("Item [id = %d, title = %s, cost = %d]", id, title, cost);
    }

}
