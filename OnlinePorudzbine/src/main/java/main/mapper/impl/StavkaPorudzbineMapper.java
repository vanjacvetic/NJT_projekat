package main.mapper.impl;

import main.dto.impl.StavkaPorudzbineDto;
import main.entity.impl.Jelo;
import main.entity.impl.Porudzbina;
import main.entity.impl.StavkaPorudzbine;
import main.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StavkaPorudzbineMapper implements DtoEntityMapper<StavkaPorudzbineDto, StavkaPorudzbine> {

    @Override
    public StavkaPorudzbineDto toDto(StavkaPorudzbine e) {
        Long porudzbinaId = e.getPorudzbina() != null ? e.getPorudzbina().getId() : null;
        Long jeloId = e.getJelo() != null ? e.getJelo().getId() : null;

        return new StavkaPorudzbineDto(
                e.getId(),
                e.getKolicina(),
                e.getJedinicnaCena(),
                e.getIznosStavke(),
                porudzbinaId,
                jeloId
        );
    }

    @Override
    public StavkaPorudzbine toEntity(StavkaPorudzbineDto t) {
        Porudzbina porudzbina = null;
        if (t.getPorudzbinaId() != null) {
            porudzbina = new Porudzbina();
            porudzbina.setId(t.getPorudzbinaId());
        }

        Jelo jelo = null;
        if (t.getJeloId() != null) {
            jelo = new Jelo();
            jelo.setId(t.getJeloId());
        }

        StavkaPorudzbine stavkaPorudzbine = new StavkaPorudzbine();
        stavkaPorudzbine.setId(t.getId());
        stavkaPorudzbine.setKolicina(t.getKolicina());
        stavkaPorudzbine.setJedinicnaCena(t.getJedinicnaCena());
        stavkaPorudzbine.setIznosStavke(t.getIznosStavke());
        stavkaPorudzbine.setPorudzbina(porudzbina);
        stavkaPorudzbine.setJelo(jelo);

        return stavkaPorudzbine;
    }
}