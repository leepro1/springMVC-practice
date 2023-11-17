package com.soringboot.relationship.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="producer")
public class ProducerEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @ManyToMany
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity product){
        products.add(product);
    }
}
