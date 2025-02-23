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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juneau
 */

@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
    @NamedQuery(name = "Customer.findByAddressline1", query = "SELECT c FROM Customer c WHERE c.addressline1 = :addressline1"),
    @NamedQuery(name = "Customer.findByAddressline2", query = "SELECT c FROM Customer c WHERE c.addressline2 = :addressline2"),
    @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM Customer c WHERE c.city = :city"),
    @NamedQuery(name = "Customer.findByState", query = "SELECT c FROM Customer c WHERE c.state = :state"),
    @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customer.findByFax", query = "SELECT c FROM Customer c WHERE c.fax = :fax"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByCreditLimit", query = "SELECT c FROM Customer c WHERE c.creditLimit = :creditLimit")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 30)
    @Column(name = "ADDRESSLINE1")
    private String addressline1;
    @Size(max = 30)
    @Column(name = "ADDRESSLINE2")
    private String addressline2;
    @Size(max = 25)
    @Column(name = "CITY")
    private String city;
    @Size(max = 2)
    @Column(name = "STATE")
    private String state;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 12)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 12)
    @Column(name = "FAX")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 40)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CREDIT_LIMIT")
    private Integer creditLimit;
    @OneToMany(mappedBy = "customerId")
    private Collection<PoolCustomer> poolCustomerCollection;
    @JoinColumn(name = "DISCOUNT_CODE", referencedColumnName = "DISCOUNT_CODE")
    @ManyToOne(optional = false)
    private DiscountCode discountCode;
    @JoinColumn(name = "ZIP", referencedColumnName = "ZIP_CODE")
    @ManyToOne(optional = false)
    private MicroMarket zip;

    

    @XmlTransient
    public Collection<PoolCustomer> getPoolCustomerCollection() {
        return poolCustomerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.Customer[ id=" + customerId + " ]";
    }


    public @NotNull Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull Integer customerId) {
        this.customerId = customerId;
    }

    public @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@Size(max = 30) String name) {
        this.name = name;
    }

    public @Size(max = 30) String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(@Size(max = 30) String addressline1) {
        this.addressline1 = addressline1;
    }

    public @Size(max = 30) String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(@Size(max = 30) String addressline2) {
        this.addressline2 = addressline2;
    }

    public @Size(max = 25) String getCity() {
        return city;
    }

    public void setCity(@Size(max = 25) String city) {
        this.city = city;
    }

    public @Size(max = 2) String getState() {
        return state;
    }

    public void setState(@Size(max = 2) String state) {
        this.state = state;
    }

    public @Size(max = 12) String getPhone() {
        return phone;
    }

    public void setPhone(@Size(max = 12) String phone) {
        this.phone = phone;
    }

    public @Size(max = 12) String getFax() {
        return fax;
    }

    public void setFax(@Size(max = 12) String fax) {
        this.fax = fax;
    }

    public @Size(max = 40) String getEmail() {
        return email;
    }

    public void setEmail(@Size(max = 40) String email) {
        this.email = email;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public MicroMarket getZip() {
        return zip;
    }

    public void setZip(MicroMarket zip) {
        this.zip = zip;
    }
}
