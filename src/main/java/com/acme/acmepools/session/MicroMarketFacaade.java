package com.acme.acmepools.session;

import com.acme.acmepools.entity.MicroMarket;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Example of CDI Bean which is interacting with PersistenceContext to retrieve
 * values from persistence store.  This is an example of using CDI in place of EJB
 */

@Named
@ApplicationScoped
public class MicroMarketFacaade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public MicroMarketFacaade() {}

    @Transactional
    public List<MicroMarket> findAll() {
        List<MicroMarket> items = null;
        {
            items = em.createNamedQuery("MicroMarket.findAll", MicroMarket.class).getResultList();
        }
        return items;
    }
}
