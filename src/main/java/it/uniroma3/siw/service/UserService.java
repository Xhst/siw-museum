package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    protected final UserRepository userRepository;

    public boolean existsById(Long id) {
        return this.userRepository.existsById(id);
    }
}
