package main.mapper.impl;

import main.dto.impl.KategorijaJelaDto;
import main.entity.impl.KategorijaJela;
import main.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class KategorijaJelaMapper implements DtoEntityMapper<KategorijaJelaDto, KategorijaJela> {

    @Override
    public KategorijaJelaDto toDto(KategorijaJela e) {
        return new KategorijaJelaDto(
                e.getId(),
                e.getNaziv(),
                e.getOpis()
        );
    }

    @Override
    public KategorijaJela toEntity(KategorijaJelaDto t) {
        KategorijaJela kategorijaJela = new KategorijaJela();
        kategorijaJela.setId(t.getId());
        kategorijaJela.setNaziv(t.getNaziv());
        kategorijaJela.setOpis(t.getOpis());
        return kategorijaJela;
    }
}