package de.gedoplan.javaee.resource;

import de.gedoplan.javaee.model.ListValue;
import de.gedoplan.javaee.repository.PublisherRepository;
import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Path("publisher")
@Api(value = "Publisher") // minium Konfiguration für Swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@PermitAll
public class PublisherResource {
    @Inject
    private PublisherRepository publisherRepository;

    @GET
    @Path("list")
    public List<ListValue> getListValues(){
        return this.publisherRepository.getListValues();
    }
}
