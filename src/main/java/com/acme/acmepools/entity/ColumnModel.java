/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juneau
 */

@Entity
@Table(name = "COLUMN_MODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColumnModel.findAll", query = "SELECT c FROM ColumnModel c"),
    @NamedQuery(name = "ColumnModel.findById", query = "SELECT c FROM ColumnModel c WHERE c.id = :id"),
    @NamedQuery(name = "ColumnModel.findByColumnName", query = "SELECT c FROM ColumnModel c WHERE c.columnName = :columnName"),
    @NamedQuery(name = "ColumnModel.findByColumnLabel", query = "SELECT c FROM ColumnModel c WHERE c.columnLabel = :columnLabel")})
public class ColumnModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Size(max = 150)
    @Column(name = "COLUMN_LABEL")
    private String columnLabel;

    public @NotNull BigDecimal getId() {
        return id;
    }

    public void setId(@NotNull BigDecimal id) {
        this.id = id;
    }

    public @Size(max = 30) String getColumnName() {
        return columnName;
    }

    public void setColumnName(@Size(max = 30) String columnName) {
        this.columnName = columnName;
    }

    public @Size(max = 150) String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(@Size(max = 150) String columnLabel) {
        this.columnLabel = columnLabel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColumnModel)) {
            return false;
        }
        ColumnModel other = (ColumnModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.entity.ColumnModel[ id=" + id + " ]";
    }

}