package main.servis;

import main.dto.impl.AuthResponseDto;
import main.dto.impl.LoginRequestDto;
import main.dto.impl.RegisterRequestDto;
import main.entity.impl.Korisnik;
import main.repository.impl.KorisnikRepository;
import main.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServis {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServis(KorisnikRepository korisnikRepository,
                      PasswordEncoder passwordEncoder,
                      JwtService jwtService,
                      AuthenticationManager authenticationManager) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto register(RegisterRequestDto dto) {

        if (korisnikRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Korisnik sa ovom email adresom već postoji.");
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(dto.getIme());
        korisnik.setPrezime(dto.getPrezime());
        korisnik.setEmail(dto.getEmail());
        korisnik.setLozinka(passwordEncoder.encode(dto.getLozinka()));
        korisnik.setBrojTelefona(dto.getBrojTelefona());
        korisnik.setRola(dto.getRola());

        korisnikRepository.save(korisnik);

        String token = jwtService.generateToken(korisnik.getEmail());

        return new AuthResponseDto(
                token,
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getEmail(),
                korisnik.getRola()
        );
    }

    public AuthResponseDto login(LoginRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getLozinka()
                )
        );

        Korisnik korisnik = korisnikRepository.findByEmail(dto.getEmail());

        if (korisnik == null) {
            throw new RuntimeException("Korisnik nije pronađen.");
        }

        String token = jwtService.generateToken(korisnik.getEmail());

        return new AuthResponseDto(
                token,
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getEmail(),
                korisnik.getRola()
        );
    }
}