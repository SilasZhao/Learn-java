package com.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //手机号验证规则： 158 后头随便
        String check = "158\\d{8}";
        Pattern regex = Pattern.compile(check);
        String phone = Optional.ofNullable(value).orElse("");
        Matcher matcher = regex.matcher(phone);


        return matcher.matches();
    }
}
