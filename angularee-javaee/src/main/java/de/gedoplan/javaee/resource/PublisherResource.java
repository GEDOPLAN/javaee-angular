package de.gedoplan.javaee.resource;

import de.gedoplan.javaee.model.ListValue;
import de.gedoplan.javaee.repository.PublisherRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PublisherResource {
    @Inject
    private PublisherRepository publisherRepository;

    @GET
    @Path("list")
    public List<ListValue> getListValues(){
        return this.publisherRepository.getListValues();
    }
}
