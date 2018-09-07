package de.gedoplan.javaee.resource;

import de.gedoplan.javaee.service.AuthenticationService;
import de.gedoplan.javaee.system.security.JWTAuth;
import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Path("login")
@Api(value = "Login") // minium Konfiguration für Swagger
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

     @Inject
     private AuthenticationService authenticationService;
    
    @POST
    public Response login(JWTAuth auth){
         String token = authenticationService.login(auth.getUser(), auth.getPassword());
         if (token==null)
         {
             return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
         } else {
             return Response.ok().header("Authorization", "BEARER " + token).build();
         }
    }
}
