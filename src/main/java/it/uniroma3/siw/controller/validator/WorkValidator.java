package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.controller.dto.WorkDto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WorkValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {
        WorkDto work = (WorkDto) o;

        String title = work.getTitle().trim();
        String imageUrl = work.getImageUrl().trim();

        if (title.isEmpty())
            errors.rejectValue("title", "error.required");

        if (imageUrl.isEmpty())
            errors.rejectValue("imageUrl", "error.required");

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return WorkDto.class.equals(clazz);
    }

}

