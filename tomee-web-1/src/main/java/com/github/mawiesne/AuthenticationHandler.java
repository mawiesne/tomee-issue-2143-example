package com.github.mawiesne;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Class handling authentication by providing {@link AuthenticationHandler#login()}
 * and {@link AuthenticationHandler#logout()} methods.
 *
 * Contributed by Martin Wiesner (@mawiesne)
 */
@Named
@RequestScoped
public class AuthenticationHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);

    @NotNull
    private String userName = "admin";

    @NotNull
    private String password;

    @Inject
    private UserSessionBean userSessionBean;

    private boolean rememberMe;

    /**
     * Tries to login the user. If the user could be logged in successfully, the page that the user intended to visit is
     * displayed. E.g. if the user wanted to visit a specific configuration, the user is forwarded to that page. If the
     * user just visited the login-page, the home page is presented. If authentication fails, an error flash message is
     * displayed to the user.
     *
     * @throws IOException if redirect cannot be executed
     */
    public void login() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userName, password, false));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            if (savedRequest == null) {
                userSessionBean.setUserName(userName);
                Faces.redirect("s/home");
            } else {
                Faces.redirect(savedRequest.getRequestUrl());
            }
        } catch (AuthenticationException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Performs a logout by invalidating the Session and then redirect to the login-page.
     *
     * @throws IOException if redirect cannot be executed
     */
    public void logout() throws IOException {
        logger.debug("Logout");
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect("login.xhtml");
    }

    /**
     * Checks whether the user visiting the login-page is already logged in and then redirects him to the home page.
     *
     * @return the home-page if the user is already logged in or null, if the user is not logged in yet
     */
    public String redirectToHome() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "pretty:home";
        }
        return null;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * @param rememberMe the rememberMe to set
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}