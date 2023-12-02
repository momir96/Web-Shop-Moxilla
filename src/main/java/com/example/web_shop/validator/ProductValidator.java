package com.example.web_shop.validator;

import com.example.web_shop.entity.Product;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.not_empty");

        if (product.getName().length() <= 1){
            errors.rejectValue("name", "product.error.name.less_2");
        }
        if (product.getName().length() > 32) {
            errors.rejectValue("name", "product.error.name.over_32");
        }
    }
}
