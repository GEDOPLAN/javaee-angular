package de.gedoplan.javaee.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.gedoplan.javaee.system.security.JWTAuth;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class AuthenticationService {

    Algorithm algorithmHS = Algorithm.HMAC256("mysecretkey");
            JWTVerifier verifier = JWT.require(algorithmHS).build(); 
    
    public JWTAuth validateToken(String token) {
        DecodedJWT jwt;
        try{
            jwt = verifier.verify(token);
        } catch (Exception e){
            return null;
        }
        JWTAuth auth=new JWTAuth();
        auth.setUser(jwt.getIssuer());
        auth.setRoles(new HashSet<>(jwt.getClaim("groups").asList(String.class)));
        return auth;
    }

    public String login(String username, String password) {
        // User gegen Datenbankprüfen etc.
        if (username.equals(password)) {
            return JWT.create()
                    .withIssuer(username)
                    .withArrayClaim("groups", new String[]{"user"})
                    .sign(algorithmHS);
        } else {
            return null;
        }
    }

}
