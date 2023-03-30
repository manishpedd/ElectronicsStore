package com.electronics.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDtoEntity {

    @Column(name="created_date",updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name="updated_date",insertable = false )
    private LocalDate updatedDate;
    @Column(name="is_active_switch")
    private String isactive;

}
