/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Pool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class PoolFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public PoolFacade() {
        
    }

    @Transactional
    public List<Pool> findAll() {
        List<Pool> items = null;
        {
            items = em.createNamedQuery("Pool.findAll", Pool.class).getResultList();
        }
        return items;
    }

    @Transactional
    public void create(Pool Pool) {
        em.persist(Pool);
    }

    @Transactional
    public Pool find(Integer poolId) {
        Pool item = null;
        try {
            item = (Pool) em.createQuery("select object(o) from Pool o where o.id = :poolId")
                    .setParameter("poolId",poolId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println("PoolFacade#find ERROR: " + e.getMessage());
        }
        return item;
    }

    @Transactional
    public void edit(Pool Pool) {
        em.merge(Pool);
    }

    @Transactional
    public void remove(Pool Pool) {
        em.remove(Pool);
    }
    
}
