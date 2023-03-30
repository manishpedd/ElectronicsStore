package com.electronics.store.entities;

import com.sun.istack.NotNull;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name="name_info")
    private String name;

    @Column (name="email")
    private String email;

    @Column(name="about_info",length = 1000)
    private String about;

    @Column(name="password_info",length = 15)
    private String password;

    @Column(name="gender_info")
    private String gender;

    @Column(name="image_info")
    private String imageName;



}

