package com.axlkike.currency.repositories;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.CurrencySearch;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CurrencySearchRepository  {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * save
     * @param currencySearch
     */
    public void save(CurrencySearch currencySearch) {
        entityManager.persist(currencySearch);
        entityManager.flush();
    }


    public List<CurrencySearch> findAllByUserId(String userId) {
        return entityManager.createQuery("from CurrencySearch  where userId = :userId order by id desc")
                .setParameter("userId", userId).setMaxResults(10).getResultList();
    }


    public List<CurrencySearch> findAll() {
        return entityManager.createQuery("from CurrencySearch").getResultList();
    }

}