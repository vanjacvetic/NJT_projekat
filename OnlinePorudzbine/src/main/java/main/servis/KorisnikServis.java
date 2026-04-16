package main.servis;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.impl.KorisnikDto;
import main.entity.impl.Korisnik;
import main.mapper.impl.KorisnikMapper;
import main.repository.impl.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikServis {

    private final KorisnikRepository korisnikRepository;
    private final KorisnikMapper korisnikMapper;

    @Autowired
    public KorisnikServis(KorisnikRepository korisnikRepository,
                          KorisnikMapper korisnikMapper) {
        this.korisnikRepository = korisnikRepository;
        this.korisnikMapper = korisnikMapper;
    }

    public List<KorisnikDto> findAll() {
        return korisnikRepository.findAll()
                .stream()
                .map(korisnikMapper::toDto)
                .collect(Collectors.toList());
    }

    public KorisnikDto findById(Long id) throws Exception {
        return korisnikMapper.toDto(korisnikRepository.findById(id));
    }

    public KorisnikDto create(KorisnikDto dto) {

        if (korisnikRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email već postoji!");
        }

        Korisnik korisnik = korisnikMapper.toEntity(dto);
        korisnikRepository.save(korisnik);

        return korisnikMapper.toDto(korisnik);
    }

    public KorisnikDto update(KorisnikDto dto) {

        Korisnik korisnik = korisnikMapper.toEntity(dto);
        korisnikRepository.save(korisnik);

        return korisnikMapper.toDto(korisnik);
    }

    public void deleteById(Long id) {
        korisnikRepository.deleteById(id);
    }
}