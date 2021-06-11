package it.uniroma3.siw.service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    protected final UserRepository userRepository;

    public boolean existsById(Long id) {
        return this.userRepository.existsById(id);
    }

    public User getById(Long id) {
        return this.userRepository.findById(id)
                .orElse(null);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
