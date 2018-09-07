package de.gedoplan.javaee.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gedoplan.javaee.jackson.views.GlobalViews;
import de.gedoplan.javaee.model.Author;
import de.gedoplan.javaee.model.ListValue;
import de.gedoplan.javaee.repository.AuthorRepository;
import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Path("author")
@Api(value = "Author") // minium Konfiguration für Swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@PermitAll
public class AuthorResource {

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private ObjectMapper mapper;

    @GET
    @Path("list")
    public List<ListValue> getListValues() {
        return this.authorRepository.getListValues();
    }

    @GET
    @Path("{id}")
    public Response getAuthor(@PathParam("id") Integer id) throws JsonProcessingException {
        Author authorById = this.authorRepository.getAuthorById(id);
        if (authorById == null) {
            return Response.status(404, "Book with id: " + id + " not found").build();
        } else {
            
            String response=this.mapper
                    .writerFor(Author.class)
                    .withView(GlobalViews.Overview.class)
                    .withAttribute("bookcount", authorById.getBooks().size())
                    .writeValueAsString(authorById);
            
            return Response.ok(response).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateAuthor(@PathParam("id") Integer id, Author authorJson) throws JsonMappingException {
        Author dbAuthor = this.authorRepository.getAuthorById(id);
        dbAuthor = mapper.updateValue(dbAuthor, authorJson);
        dbAuthor= this.authorRepository.merge(dbAuthor);
        return Response.ok().build();
    }
}
