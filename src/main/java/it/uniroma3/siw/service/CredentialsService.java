package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Credentials;

import it.uniroma3.siw.model.Provider;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CredentialsService {

    protected final PasswordEncoder passwordEncoder;

    protected final CredentialsRepository credentialsRepository;


    public Credentials getById(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

    public Credentials getByUsername(String username) {
        Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
        return result.orElse(null);
    }

    public Credentials save(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setProvider(Provider.LOCAL);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));

        return this.credentialsRepository.save(credentials);
    }

    public void processOAuthPostLogin(String email, String firstName, String lastName) {
        Optional<Credentials> result = this.credentialsRepository.findByUsername(email);

        if (result.isPresent()) return;

        User user = new User(firstName, lastName);
        user.setEmail(email);

        Credentials credentials = new Credentials();
        credentials.setUsername(email);
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setProvider(Provider.GOOGLE);
        credentials.setUser(user);

        this.credentialsRepository.save(credentials);
    }

}
