package com.electronics.store.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto extends BaseDtoEntity{

    private String productId;
    private String title;
    private String description;
    private Integer quantity;
    private Date addedDate;
    private Boolean live;
    private Boolean stock;
    private Integer price;
    private Integer discountedPrice;


}
