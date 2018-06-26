package com.github.mawiesne;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Contributed by Richard Zowalla (rzo1) -  15.02.2017.
 */
public interface CookieWSEndpoint   {

    String API_PATH = "/cookie/v0.5";

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("cookie")
    String getCookie();
}
