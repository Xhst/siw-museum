package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class CredentialsValidator implements Validator {

    private final CredentialsService credentialsService;

    final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 6;


    @Override
    public void validate(Object o, Errors errors) {
        Credentials credentials = (Credentials) o;
        String username = credentials.getUsername().trim();
        String password = credentials.getPassword().trim();

        if (username.isEmpty())
            errors.rejectValue("username", "error.required");
        else if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH)
            errors.rejectValue("username", "error.size");
        else if (this.credentialsService.getByUsername(username) != null)
            errors.rejectValue("username", "error.duplicate");

        if (password.isEmpty())
            errors.rejectValue("password", "error.required");
        else if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
            errors.rejectValue("password", "error.size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

}