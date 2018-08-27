package de.gedoplan.javaee.system.security;

import java.util.Set;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
public class JWTAuth {

    private String token;

    private String user;
    
    private String password;

    private Set<String> roles;

    public JWTAuth() {
    }

    public JWTAuth(String token, String user, Set<String> roles) {
        this.token = token;
        this.user = user;
        this.roles = roles;
    }
    
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
