package de.gedoplan.javaee.system;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> proprties = new HashMap<>();
        proprties.put("jersey.config.server.disableMoxyJson", true); //Glassfish < 4
        proprties.put("jersey.config.jsonFeature", "JacksonFeature"); // Glassfish >= 5
        
        //Swagger Config
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setTitle("JavaEE + Angular");
        beanConfig.setBasePath("http://localhost:8002/resources");
        beanConfig.setResourcePackage("de.gedoplan.javaee.resource");
        beanConfig.setScan(true);
        return proprties;
    }

}
