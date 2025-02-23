/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.ColumnModel;
import com.acme.acmepools.entity.Customer;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
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
public class ColumnModelFacade implements Serializable {
    @PersistenceContext(unitName = "com.acme_AcmePools_war_AcmePools-1.0-SNAPSHOTPU")
    private EntityManager em;

    public ColumnModelFacade() {

    }

    @Transactional
    public List<ColumnModel> findAll() {
        List<ColumnModel> items = null;
        {
            items = em.createQuery("select object(o) from ColumnModel o").getResultList();
        }
        return items;
    }

    @Transactional
    public ColumnModel findId(String columnName){
        return (ColumnModel) em.createQuery("select object(o) from ColumnModel as o " +
                              "where o.columnName = :columnName")
                              .setParameter("columnName", columnName)
                              .getSingleResult();
    }
    
}
