package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.dto.CollectionDto;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class CollectionValidator implements Validator {

    private final UserService userService;

    @Override
    public void validate(Object o, Errors errors) {
        CollectionDto collection = (CollectionDto) o;

        String name = collection.getName().trim();
        String description = collection.getDescription().trim();
        Long curatorId = collection.getCuratorId();

        if (name.isEmpty())
            errors.rejectValue("name", "error.required");

        if (description.isEmpty())
            errors.rejectValue("description", "error.required");

        User user = this.userService.getById(curatorId);

        if (user == null) {
            errors.reject("error.notExists");
        }

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CollectionDto.class.equals(clazz);
    }

}

