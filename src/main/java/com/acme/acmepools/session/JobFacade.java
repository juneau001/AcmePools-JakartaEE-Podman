/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Job;
import com.acme.acmepools.entity.Job;
import com.acme.acmepools.entity.MicroMarket;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import javax.print.attribute.standard.JobSheets;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class JobFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public JobFacade() {
    }

    @Transactional
    public List<Job> findAll() {
        List<Job> items = null;
        {
            items = em.createNamedQuery("Job.findAll", Job.class).getResultList();
        }
        return items;
    }

    @Transactional
    public void create(Job Job) {
        em.persist(Job);
    }

    @Transactional
    public Job find(Integer jobId) {
        Job item = null;
        try {
            item = (Job) em.createQuery("select object(o) from Job o where o.id = :jobId")
                    .setParameter("jobId",jobId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println("JobFacade#find ERROR: " + e.getMessage());
        }
        return item;
    }

    @Transactional
    public void edit(Job Job) {
        em.merge(Job);
    }

    @Transactional
    public void remove(Job Job) {
        em.remove(Job);
    }
    
}
