package com.acme.acmepools.session;

import com.acme.acmepools.entity.DiscountCode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class DiscountCodeFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public DiscountCodeFacade() {}

    protected EntityManager getEntityManager() {
        return null;
    }

    @Transactional
    public List<DiscountCode> findAll() {
        List<DiscountCode> items = null;
        {
            items = em.createNamedQuery("DiscountCode.findAll", DiscountCode.class).getResultList();
        }
        return items;
    }
}
