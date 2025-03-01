package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.PoolCustomer;
import com.acme.acmepools.session.PoolCustomerFacade;
import com.acme.acmepools.entity.util.JsfUtil;
import com.acme.acmepools.entity.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@Named("poolCustomerController")
@SessionScoped
public class PoolCustomerController implements Serializable {

    @Inject
    private com.acme.acmepools.session.PoolCustomerFacade cdiFacade;
    private List<PoolCustomer> items = null;
    private PoolCustomer selected;

    public PoolCustomerController() {
    }

    public PoolCustomer getSelected() {
        return selected;
    }

    public void setSelected(PoolCustomer selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PoolCustomerFacade getFacade() {
        return cdiFacade;
    }

    public PoolCustomer prepareCreate() {
        selected = new PoolCustomer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PoolCustomerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PoolCustomerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PoolCustomerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PoolCustomer> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } 
        }
    }

    public PoolCustomer getPoolCustomer(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PoolCustomer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PoolCustomer> getItemsAvailableSelectOne() {
        List<PoolCustomer> customers = new ArrayList<PoolCustomer>();
        try {
            customers = getFacade().findAll();
        } catch (Exception ex) {
            System.out.println("PoolCustomerController#getItemsAvailableSelectOne ERROR: " + ex.getMessage());
        }
        return customers;
    }

    @FacesConverter(forClass = PoolCustomer.class)
    public static class PoolCustomerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PoolCustomerController controller = (PoolCustomerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "poolCustomerController");
            return controller.getPoolCustomer(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PoolCustomer) {
                PoolCustomer o = (PoolCustomer) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PoolCustomer.class.getName()});
                return null;
            }
        }

    }

}
