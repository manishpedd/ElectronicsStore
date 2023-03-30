package com.electronics.store.dtos;

import com.electronics.store.validate.ImageNameValidate;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends BaseDtoEntity{



    private Integer userId;
    @NotEmpty
    @Size(min = 4, message = "username must be of atleast 4 characters")
    private String name;

    @Email(message = "your given email address is not valid ")
    private String email;
    @NotEmpty
    @Size(min = 10 ,message= "Write something about yourself")
    private String about;
    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be of minimum 3 characters and maximum of 10 characters")
    private String password;

    private String gender;

    @ImageNameValidate
    private String imageName;

}
