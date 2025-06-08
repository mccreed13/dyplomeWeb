package com.example.dyplomeweb.entity;

import com.example.dyplomeweb.enums.Brands;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    private Brands brand;
    private String address;
}
