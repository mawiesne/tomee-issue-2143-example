package com.github.mawiesne;

import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Contributed by Martin Wiesner (@mawiesne)
 */
@Named
@ViewScoped
public class CookieHandler implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CookieHandler.class);
    private static final long serialVersionUID = 4580408330853047682L;

    @Inject
    private transient CakeService anotherStatelessService;

    @Inject
    private transient BakeryService bakeryService;

    @Inject
    private transient BunService bunService;

    public void produceCookie() {
        try {
            Faces.redirect("s/cookie");
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    public String getCookieProductionTime() {

        anotherStatelessService.makeCake();
        bakeryService.produce();
        bunService.bun();

        Date serverTime = new Date();
        return serverTime.toString();
    }
}
