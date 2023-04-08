package com.electronics.store.dtos;

import lombok.*;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseDtoEntity {


    private LocalDate createdDate;

    private LocalDate updatedDate;
    private String isactive;

}
