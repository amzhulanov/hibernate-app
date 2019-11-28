package com.flamexander.hibernate.hw3;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = -2750973356670718107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private float cost;

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

    public float getCost() {
        return cost;
    }

    public void setPrice(float cost) {
        this.cost = cost;
    }

    public Products() {
    }

    public Products(String title, float cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Products [id = %d, title = %s, cost = %f]", id, title, cost);
    }
}