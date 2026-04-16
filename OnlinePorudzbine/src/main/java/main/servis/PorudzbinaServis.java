package main.servis;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.impl.PorudzbinaDto;
import main.entity.impl.Porudzbina;
import main.mapper.impl.PorudzbinaMapper;
import main.repository.impl.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorudzbinaServis {

    private final PorudzbinaRepository repository;
    private final PorudzbinaMapper mapper;

    @Autowired
    public PorudzbinaServis(PorudzbinaRepository repository,
                           PorudzbinaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PorudzbinaDto> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PorudzbinaDto findById(Long id) throws Exception {

        return mapper.toDto(repository.findById(id));
    }

    public PorudzbinaDto create(PorudzbinaDto dto) {

        Porudzbina porudzbina = mapper.toEntity(dto);
        repository.save(porudzbina);

        return mapper.toDto(porudzbina);
    }

    public PorudzbinaDto update(PorudzbinaDto dto) {

        Porudzbina porudzbina = mapper.toEntity(dto);
        repository.save(porudzbina);

        return mapper.toDto(porudzbina);
    }

    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    public List<PorudzbinaDto> findByKorisnikId(Long korisnikId) {

        return repository.findByKorisnikId(korisnikId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}