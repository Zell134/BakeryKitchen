package com.bakery.bakeryKitchen.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Production")
@Data
@AllArgsConstructor
public class Product implements Serializable {
    
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    
    @Column(length = 2048)
    private String description;
    private BigDecimal price;
    private boolean active;
    private long type;
    private String imageUrl;
    public String getPrice() {
        return String.valueOf(this.price);
    }

    public Product() {
        this.name = "";
        this.description = "";
        this.price = new BigDecimal("0");
        this.active = true;
        this.type = 0;
        this.imageUrl = "";
    }

}
