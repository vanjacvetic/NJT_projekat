package main.servis;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.impl.KategorijaJelaDto;
import main.entity.impl.KategorijaJela;
import main.mapper.impl.KategorijaJelaMapper;
import main.repository.impl.KategorijaJelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KategorijaJelaServis {

    private final KategorijaJelaRepository repository;
    private final KategorijaJelaMapper mapper;

    @Autowired
    public KategorijaJelaServis(KategorijaJelaRepository repository,
                               KategorijaJelaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<KategorijaJelaDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public KategorijaJelaDto findById(Long id) throws Exception {
        return mapper.toDto(repository.findById(id));
    }

    public KategorijaJelaDto create(KategorijaJelaDto dto) {

        KategorijaJela kategorija = mapper.toEntity(dto);
        repository.save(kategorija);

        return mapper.toDto(kategorija);
    }

    public KategorijaJelaDto update(KategorijaJelaDto dto) {

        KategorijaJela kategorija = mapper.toEntity(dto);
        repository.save(kategorija);

        return mapper.toDto(kategorija);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}