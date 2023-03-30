package com.electronics.store.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValidate {

    //Error message
    String message() default "Invalid image name";

    //Represent group of constraints
    Class<?>[] groups() default{};

    //Additional information about annotations
    Class<? extends Payload>[] payload() default{};

}
