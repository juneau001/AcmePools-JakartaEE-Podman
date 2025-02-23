package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.DiscountCode;
import com.acme.acmepools.session.DiscountCodeFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class DiscountCodeController {

    @Inject
    DiscountCodeFacade cdiFacade;

    public DiscountCodeController() {}

    public List<DiscountCode> getItemsAvailableSelectOne() {
        return cdiFacade.findAll();
    }
}
