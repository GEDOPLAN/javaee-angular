package de.gedoplan.javaee.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class GlobalCDIProducer {

    @PersistenceContext(unitName = "demo")
    @Produces
    private EntityManager em;

    private ObjectMapper mapper;
    
    @Produces
    public ObjectMapper getMapper(){
        if(this.mapper==null){
            //init mapper
            //...addtional settings, e.g. dateformat
            this.mapper=new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.mapper.setDateFormat(df);
        }
        
        return this.mapper;
    }
    
}
