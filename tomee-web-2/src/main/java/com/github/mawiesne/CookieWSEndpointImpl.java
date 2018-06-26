package com.github.mawiesne;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.Date;

/**
 * Contributed by Richard Zowalla (rzo1) -  15.02.2017.
 */
@Stateless
@Path(CookieWSEndpoint.API_PATH)
public class CookieWSEndpointImpl implements CookieWSEndpoint {

    @Inject
    private CakeService anotherStatelessService;

    @Inject
    private BakeryService bakeryService;

    @Inject
    private BunService bunService;

    @Override
    public String getCookie() {

        anotherStatelessService.makeCake();
        bakeryService.produce();
        bunService.bun();

        Date serverTime = new Date();

        return serverTime.toString();
    }
}
