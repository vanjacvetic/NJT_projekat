package main.mapper.impl;

import main.dto.impl.PorudzbinaDto;
import main.entity.impl.Korisnik;
import main.entity.impl.Porudzbina;
import main.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class PorudzbinaMapper implements DtoEntityMapper<PorudzbinaDto, Porudzbina> {

    @Override
    public PorudzbinaDto toDto(Porudzbina e) {
        Long korisnikId = e.getKorisnik() != null ? e.getKorisnik().getId() : null;

        return new PorudzbinaDto(
                e.getId(),
                e.getAdresa(),
                e.getKontakt(),
                e.getNapomena(),
                e.getUkupanIznos(),
                korisnikId,
                e.getStatus()
        );
    }

    @Override
    public Porudzbina toEntity(PorudzbinaDto t) {
        Korisnik korisnik = null;
        if (t.getKorisnikId() != null) {
            korisnik = new Korisnik();
            korisnik.setId(t.getKorisnikId());
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setId(t.getId());
        porudzbina.setAdresa(t.getAdresa());
        porudzbina.setKontakt(t.getKontakt());
        porudzbina.setNapomena(t.getNapomena());
        porudzbina.setUkupanIznos(t.getUkupanIznos());
        porudzbina.setKorisnik(korisnik);
        porudzbina.setStatus(t.getStatus());

        return porudzbina;
    }
}