package com.github.mawiesne;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Contributed by Richard Zowalla (rzo1) -  12.05.2017.
 */
@Stateless
public class BunServiceImpl extends AbstractService implements BunService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BunServiceImpl.class);

    @Inject
    private CakeService anotherStatelessService;

    @Inject
    private CookieService cookieService;

    @Override
    public void bun() {
        anotherStatelessService.makeCake();
        cookieService.bake();
    }
}
