package main.servis;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.impl.JeloDto;
import main.entity.impl.Jelo;
import main.mapper.impl.JeloMapper;
import main.repository.impl.JeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeloServis {

    private final JeloRepository repository;
    private final JeloMapper mapper;

    @Autowired
    public JeloServis(JeloRepository repository,
                      JeloMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<JeloDto> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public JeloDto findById(Long id) throws Exception {

        return mapper.toDto(repository.findById(id));
    }

    public JeloDto create(JeloDto dto) {

        Jelo jelo = mapper.toEntity(dto);
        repository.save(jelo);

        return mapper.toDto(jelo);
    }

    public JeloDto update(JeloDto dto) {

        Jelo jelo = mapper.toEntity(dto);
        repository.save(jelo);

        return mapper.toDto(jelo);
    }

    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    public List<JeloDto> findByKategorijaId(Long kategorijaId) {

        return repository.findByKategorijaId(kategorijaId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<JeloDto> findDostupnaJela() {

        return repository.findDostupnaJela()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}