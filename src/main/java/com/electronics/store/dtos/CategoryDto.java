package com.electronics.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 4, message = "title must be of atleast 4 characters")
    private String title;
    @NotEmpty
    @Size(min = 10,max=100, message = "Description must be of atleast 10 characters")
    private String description;
    private String coverImage;


}


