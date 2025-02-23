/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.PoolCustomer;
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
public class PoolCustomerFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public PoolCustomerFacade() {
        
    }

    @Transactional
    public List<PoolCustomer> findAll() {
        List<PoolCustomer> items = null;
        {
            items = em.createNamedQuery("PoolCustomer.findAll", PoolCustomer.class).getResultList();
        }
        return items;
    }

    @Transactional
    public void create(PoolCustomer PoolCustomer) {
        em.persist(PoolCustomer);
    }

    @Transactional
    public PoolCustomer find(Integer poolCustomerId) {
        PoolCustomer item = null;
        try {
            item = (PoolCustomer) em.createQuery("select object(o) from PoolCustomer o where o.id = :poolCustomerId")
                    .setParameter("poolCustomerId",poolCustomerId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println("PoolCustomerFacade#find ERROR: " + e.getMessage());
        }
        return item;
    }

    @Transactional
    public void edit(PoolCustomer PoolCustomer) {
        em.merge(PoolCustomer);
    }

    @Transactional
    public void remove(PoolCustomer PoolCustomer) {
        em.remove(PoolCustomer);
    }
    
}
