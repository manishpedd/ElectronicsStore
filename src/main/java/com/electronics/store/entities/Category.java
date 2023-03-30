package com.electronics.store.entities;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    @Column(name = "category_title", length = 30)
    private String title;
    @Column(name = "category_description", length = 100)
    private String description;

    @Column(name = "category_coverimage", length = 15)
    private String coverImage;


}
