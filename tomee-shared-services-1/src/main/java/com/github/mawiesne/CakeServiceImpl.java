package com.github.mawiesne;

import javax.ejb.Stateless;

/**
 * Contributed by Richard Zowalla (rzo1) -  12.05.2017.
 */
@Stateless
public class CakeServiceImpl extends AbstractService implements CakeService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CakeServiceImpl.class);

    @Override
    public void makeCake() {
        logger.info("Delicious Cake");
    }
}
