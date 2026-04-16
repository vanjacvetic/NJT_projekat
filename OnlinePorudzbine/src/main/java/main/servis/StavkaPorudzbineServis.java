package main.servis;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.impl.StavkaPorudzbineDto;
import main.entity.impl.StavkaPorudzbine;
import main.mapper.impl.StavkaPorudzbineMapper;
import main.repository.impl.StavkaPorudzbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaPorudzbineServis {

    private final StavkaPorudzbineRepository repository;
    private final StavkaPorudzbineMapper mapper;

    @Autowired
    public StavkaPorudzbineServis(StavkaPorudzbineRepository repository,
                                 StavkaPorudzbineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<StavkaPorudzbineDto> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public StavkaPorudzbineDto findById(Long id) throws Exception {

        return mapper.toDto(repository.findById(id));
    }

    public StavkaPorudzbineDto create(StavkaPorudzbineDto dto) {

        StavkaPorudzbine stavka = mapper.toEntity(dto);
        repository.save(stavka);

        return mapper.toDto(stavka);
    }

    public StavkaPorudzbineDto update(StavkaPorudzbineDto dto) {

        StavkaPorudzbine stavka = mapper.toEntity(dto);
        repository.save(stavka);

        return mapper.toDto(stavka);
    }

    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    public List<StavkaPorudzbineDto> findByPorudzbinaId(Long porudzbinaId) {

        return repository.findByPorudzbinaId(porudzbinaId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}