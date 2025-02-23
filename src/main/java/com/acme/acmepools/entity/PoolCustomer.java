/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.entity;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "POOL_CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoolCustomer.findAll", query = "SELECT p FROM PoolCustomer p"),
    @NamedQuery(name = "PoolCustomer.findById", query = "SELECT p FROM PoolCustomer p WHERE p.id = :id")})
public class PoolCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne
    private Customer customerId;
    @JoinColumn(name = "POOL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Pool poolId;
    @OneToMany(mappedBy = "customerId")
    private Collection<Job> jobCollection;

    public PoolCustomer() {
    }

    public PoolCustomer(Integer id) {
        this.setId(id);
    }

    
    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoolCustomer)) {
            return false;
        }
        PoolCustomer other = (PoolCustomer) object;
        if ((this.getId() == null && other.id != null) || (this.getId() != null && !this.getId().equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.PoolCustomer[ id=" + getId() + " ]";
    }


    public @NotNull Integer getId() {
        return id;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Pool getPoolId() {
        return poolId;
    }

    public void setPoolId(Pool poolId) {
        this.poolId = poolId;
    }
}
