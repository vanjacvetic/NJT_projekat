package main.mapper.impl;

import main.dto.impl.JeloDto;
import main.entity.impl.Jelo;
import main.entity.impl.KategorijaJela;
import main.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class JeloMapper implements DtoEntityMapper<JeloDto, Jelo> {

    @Override
    public JeloDto toDto(Jelo e) {
        Long kategorijaJelaId = e.getKategorijaJela() != null ? e.getKategorijaJela().getId() : null;

        return new JeloDto(
                e.getId(),
                e.getNaziv(),
                e.getOpis(),
                e.getCena(),
                e.getJedinicaMere(),
                e.getSlika(),
                e.getDostupan(),
                kategorijaJelaId
        );
    }

    @Override
    public Jelo toEntity(JeloDto t) {
        KategorijaJela kategorijaJela = null;
        if (t.getKategorijaJelaId() != null) {
            kategorijaJela = new KategorijaJela();
            kategorijaJela.setId(t.getKategorijaJelaId());
        }

        Jelo jelo = new Jelo();
        jelo.setId(t.getId());
        jelo.setNaziv(t.getNaziv());
        jelo.setOpis(t.getOpis());
        jelo.setCena(t.getCena());
        jelo.setJedinicaMere(t.getJedinicaMere());
        jelo.setSlika(t.getSlika());
        jelo.setDostupan(t.getDostupan());
        jelo.setKategorijaJela(kategorijaJela);

        return jelo;
    }
}