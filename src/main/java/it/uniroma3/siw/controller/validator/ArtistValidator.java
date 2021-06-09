package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class ArtistValidator implements Validator {

    private final ArtistService artistService;

    @Override
    public void validate(Object o, Errors errors) {
        Artist artist = (Artist) o;

        String firstName = artist.getFirstName();
        String lastName = artist.getLastName();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required");

        if (this.artistService.getFromFullName(firstName, lastName) != null) {
            errors.reject("error.duplicate");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Artist.class.equals(clazz);
    }

}

