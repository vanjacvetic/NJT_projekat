package main.security;

import java.util.Collections;
import main.entity.impl.Korisnik;
import main.repository.impl.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;

    @Autowired
    public CustomUserDetailsService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByEmail(email);

        if (korisnik == null) {
            throw new UsernameNotFoundException("Korisnik nije pronađen sa email adresom: " + email);
        }

        return new User(
                korisnik.getEmail(),
                korisnik.getLozinka(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + korisnik.getRola().name()))
        );
    }
}