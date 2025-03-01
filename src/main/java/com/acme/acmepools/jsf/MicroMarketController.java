package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.MicroMarket;
import com.acme.acmepools.session.MicroMarketFacaade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class MicroMarketController {

    @Inject
    MicroMarketFacaade cdiFacade;

    public MicroMarketController() {}

    public List<MicroMarket> getItemsAvailableSelectOne() {
        return cdiFacade.findAll();
    }
}
