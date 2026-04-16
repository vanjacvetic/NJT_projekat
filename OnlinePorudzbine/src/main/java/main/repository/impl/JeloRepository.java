package main.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import main.entity.impl.Jelo;
import main.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JeloRepository implements MyAppRepository<Jelo, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Jelo> findAll() {
        return entityManager.createQuery("SELECT j FROM Jelo j", Jelo.class).getResultList();
    }

    @Override
    public Jelo findById(Long id) throws Exception {
        Jelo jelo = entityManager.find(Jelo.class, id);
        if (jelo == null) {
            throw new Exception("Jelo nije pronađeno!");
        }
        return jelo;
    }

    @Override
    @Transactional
    public void save(Jelo entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Jelo jelo = entityManager.find(Jelo.class, id);
        if (jelo != null) {
            entityManager.remove(jelo);
        }
    }

    public List<Jelo> findByKategorijaId(Long kategorijaId) {
        return entityManager
                .createQuery("SELECT j FROM Jelo j WHERE j.kategorijaJela.id = :kategorijaId", Jelo.class)
                .setParameter("kategorijaId", kategorijaId)
                .getResultList();
    }

    public List<Jelo> findDostupnaJela() {
        return entityManager
                .createQuery("SELECT j FROM Jelo j WHERE j.dostupan = true", Jelo.class)
                .getResultList();
    }
}