package main.mapper.impl;

import main.dto.impl.KorisnikDto;
import main.entity.impl.Korisnik;
import main.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class KorisnikMapper implements DtoEntityMapper<KorisnikDto, Korisnik> {

    @Override
    public KorisnikDto toDto(Korisnik e) {
        return new KorisnikDto(
                e.getId(),
                e.getIme(),
                e.getPrezime(),
                e.getEmail(),
                e.getLozinka(),
                e.getBrojTelefona(),
                e.getRola()
        );
    }

    @Override
    public Korisnik toEntity(KorisnikDto t) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(t.getId());
        korisnik.setIme(t.getIme());
        korisnik.setPrezime(t.getPrezime());
        korisnik.setEmail(t.getEmail());
        korisnik.setLozinka(t.getLozinka());
        korisnik.setBrojTelefona(t.getBrojTelefona());
        korisnik.setRola(t.getRola());
        return korisnik;
    }
}