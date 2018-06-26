package com.github.mawiesne;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Contributed by Richard Zowalla (rzo1) -  12.05.2017.
 */
@Singleton
@Startup
public class BakeryServiceImpl extends AbstractService implements BakeryService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BakeryServiceImpl.class);

    @Inject
    private CakeService anotherStatelessService;

    @Inject
    private CookieService cookieService;

    @PostConstruct
    @Override
    public void produce() {
        cookieService.bake();
        anotherStatelessService.makeCake();
    }
}
