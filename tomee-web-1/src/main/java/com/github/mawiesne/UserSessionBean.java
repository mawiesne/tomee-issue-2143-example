package com.github.mawiesne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Contributed by Martin Wiesner (@mawiesne)
 */
@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserSessionBean.class);
    private String userName;

    @PostConstruct
    private void init() {
        logger.debug("{} was initialized", this.getClass().getSimpleName());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
