package de.gedoplan.javaee.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {

    @Inject
    private ObjectMapper mapper;
    
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return getMapper();
    }

    public ObjectMapper getMapper() {
        // DI may not be available in used application server for provider-classes
        if (mapper==null){
            CDI.current().select(ObjectMapper.class).get();
        }
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    
}
