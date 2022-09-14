package com.project1.project1.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table (name = "products")

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductId;
    private String ProductName;
    private String ProductDescription;
    private int price;
    private boolean Active=Boolean.TRUE;
    private boolean Deleted=Boolean.FALSE;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDate CreateDate;
    @UpdateTimestamp
    LocalDate UpdateDate;
}
