package main.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import main.entity.impl.KategorijaJela;
import main.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

@Repository
public class KategorijaJelaRepository implements MyAppRepository<KategorijaJela, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<KategorijaJela> findAll() {
        return entityManager.createQuery("SELECT k FROM KategorijaJela k", KategorijaJela.class).getResultList();
    }

    @Override
    public KategorijaJela findById(Long id) throws Exception {
        KategorijaJela kategorijaJela = entityManager.find(KategorijaJela.class, id);
        if (kategorijaJela == null) {
            throw new Exception("Kategorija jela nije pronađena!");
        }
        return kategorijaJela;
    }

    @Override
    @Transactional
    public void save(KategorijaJela entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        KategorijaJela kategorijaJela = entityManager.find(KategorijaJela.class, id);
        if (kategorijaJela != null) {
            entityManager.remove(kategorijaJela);
        }
    }
}