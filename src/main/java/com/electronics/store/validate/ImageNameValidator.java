package com.electronics.store.validate;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class ImageNameValidator implements ConstraintValidator<ImageNameValidate,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        log.info("message isValid: {} ",value);

        if(value.isBlank()){
            return false;
        }else{
            return true;
        }


    }
}
