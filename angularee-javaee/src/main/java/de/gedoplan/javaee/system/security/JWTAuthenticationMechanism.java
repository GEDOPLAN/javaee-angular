package de.gedoplan.javaee.system.security;

import de.gedoplan.javaee.service.AuthenticationService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private AuthenticationService authenticationService;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        if (!httpMessageContext.isProtected()){
        return httpMessageContext.doNothing();
        }
        
        String authorizationHeader = request.getHeader("Authorization");

        String[] authTokenSplit;
        if (authorizationHeader != null && (authTokenSplit = authorizationHeader.split(" ")).length > 0) {
            JWTAuth auth = this.authenticationService.validateToken(authTokenSplit[1]);
            if (auth != null) {
                return httpMessageContext.notifyContainerAboutLogin(auth.getUser(), auth.getRoles());
            }
        }
        
        
        return httpMessageContext.responseUnauthorized();
    }

}
