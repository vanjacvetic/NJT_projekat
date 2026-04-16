package main.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import main.entity.impl.Korisnik; 
import main.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

@Repository
public class KorisnikRepository implements MyAppRepository<Korisnik, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Korisnik> findAll() {
        return entityManager.createQuery("SELECT k FROM Korisnik k", Korisnik.class).getResultList();
    }

    @Override
    public Korisnik findById(Long id) throws Exception {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if (korisnik == null) {
            throw new Exception("Korisnik nije pronađen!");
        }
        return korisnik;
    }

    @Override
    @Transactional
    public void save(Korisnik entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if (korisnik != null) {
            entityManager.remove(korisnik);
        }
    }

    public Korisnik findByEmail(String email) {
        List<Korisnik> lista = entityManager
                .createQuery("SELECT k FROM Korisnik k WHERE k.email = :email", Korisnik.class)
                .setParameter("email", email)
                .getResultList();

        return lista.isEmpty() ? null : lista.get(0);
    }

    public boolean existsByEmail(String email) {
        Long broj = entityManager
                .createQuery("SELECT COUNT(k) FROM Korisnik k WHERE k.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return broj > 0;
    }
}