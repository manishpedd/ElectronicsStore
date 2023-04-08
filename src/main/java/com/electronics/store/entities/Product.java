package com.electronics.store.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "product_details")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    @Column(name = "product_title")
    private String title;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_quantity")
    private Integer quantity;
    @Column(name = "product_added_date")
    private Date addedDate;
    @Column(name = "product_live")
    private Boolean live;
    @Column(name = "product_stock")
    private Boolean Stock;
    @Column(name = "product_price")
    private Integer price;
    @Column(name = "product_discountedprice")
    private Integer discountedPrice;


}
