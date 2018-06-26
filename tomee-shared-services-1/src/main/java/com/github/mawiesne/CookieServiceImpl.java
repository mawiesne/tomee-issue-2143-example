package com.github.mawiesne;

import javax.ejb.Stateless;

/**
 * Contributed by Richard Zowalla (rzo1) -  12.05.2017.
 */
@Stateless
public class CookieServiceImpl extends AbstractService implements CookieService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CookieServiceImpl.class);

    @Override
    public void bake() {
        logger.info("I was called.");
    }
}
