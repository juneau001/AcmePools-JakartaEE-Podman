package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.Customer;
import com.acme.acmepools.entity.util.JsfUtil;
import com.acme.acmepools.entity.util.JsfUtil.PersistAction;
import com.acme.acmepools.session.CustomerFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

@Named("customerController")
@SessionScoped
@Log4j2
public class CustomerController implements Serializable {

    @Inject
    private com.acme.acmepools.session.CustomerFacade cdiFacade;
    private List<Customer> items = null;
    @Getter @Setter
    private Customer selected;

    public CustomerController() {
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CustomerFacade getFacade() {
        return cdiFacade;
    }

    public Customer prepareCreate() {
        selected = new Customer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CustomerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CustomerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CustomerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Customer> getItems() {
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

    public Customer getCustomer(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Customer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Customer> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Customer.class)
    public static class CustomerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomerController controller = (CustomerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customerController");
            return controller.getCustomer(getKey(value));
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
            if (object instanceof Customer) {
                Customer o = (Customer) object;
                return getStringKey(o.getCustomerId());
            } else {
                log.error("object {0} is of type {1}; expected type: {2}");
                return null;
            }
        }

    }
    
        public void onCellEdit(CellEditEvent event) {  
            Object oldValue = event.getOldValue();  
            Object newValue = event.getNewValue();  
            if(!oldValue.equals(newValue)){
                // Save to the database
                DataTable table = (DataTable) event.getSource();
                Customer customer = (Customer) table.getRowData();
                cdiFacade.edit(customer);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Successfully Updated", "Updated value to " + newValue));  
            }
        }

    public String loadCustomer() {
        Map requestMap = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String customer = (String) requestMap.get("customer");
        selected = cdiFacade.find(Integer.valueOf(customer));
        return "customerInfo";
    }
    
    
}
