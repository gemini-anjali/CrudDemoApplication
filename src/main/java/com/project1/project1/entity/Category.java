package com.project1.project1.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@ToString
@Table(name="category")


public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int CategoryId;
    private String CategoryName;
    private String CategoryDescription;
    private boolean Active=Boolean.TRUE;
    private boolean Deleted=Boolean.FALSE;
    @CreationTimestamp
    @Column(updatable = false)
    LocalDate CreateDate;
    @UpdateTimestamp
    LocalDate UpdateDate;
    @OneToMany(targetEntity = Products.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
    private List<Products> products;
}

