package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.User;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        String firstName = user.getFirstName().trim();
        String lastName = user.getLastName().trim();
        String email = user.getEmail().trim();

        if (firstName.isEmpty())
            errors.rejectValue("firstName", "error.required");
        else if (firstName.length() < MIN_NAME_LENGTH || firstName.length() > MAX_NAME_LENGTH)
            errors.rejectValue("firstName", "error.size");

        if (lastName.isEmpty())
            errors.rejectValue("lastName", "error.required");
        else if (lastName.length() < MIN_NAME_LENGTH || lastName.length() > MAX_NAME_LENGTH)
            errors.rejectValue("lastName", "error.size");

        if (email.isEmpty())
            errors.rejectValue("email", "error.required");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}

