/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Customer;

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
public class CustomerFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacade() {
    }

    @Transactional
    public List<Customer> findAll() {
        List<Customer> items = null;
        {
            items = em.createQuery("select object(o) from Customer o").getResultList();
        }
        return items;
    }

    @Transactional
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public Customer find(Integer customerId) {
        Customer item = null;
        try {
            item = (Customer) em.createQuery("select object(o) from Customer o where o.customerId = :customerId")
                    .setParameter("customerId",customerId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println("CustomerFacade#find ERROR: " + e.getMessage());
        }
        return item;
    }

    @Transactional
    public void edit(Customer customer) {
        em.merge(customer);
    }

    @Transactional
    public void remove(Customer customer) {
        em.remove(customer);
    }
    
}
