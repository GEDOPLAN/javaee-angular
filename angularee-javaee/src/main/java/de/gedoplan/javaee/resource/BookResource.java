package de.gedoplan.javaee.resource;

import com.fasterxml.jackson.annotation.JsonView;
import de.gedoplan.javaee.model.Book;
import de.gedoplan.javaee.repository.BookRepository;
import de.gedoplan.javaee.jackson.views.GlobalViews;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
@Path("book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Books")
@SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
    @ApiKeyAuthDefinition(key = "jwt", name = "Authorization", in = ApiKeyLocation.HEADER)}))
@PermitAll
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Produces("application/json")
    @JsonView(GlobalViews.Overview.class)
    @ApiOperation(value = "Liefet alle Bücher")
    public List<Book> getBooks() {
        return this.bookRepository.getBooks();
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Liefert ein einzelnes Buch anhand der ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Buch gefunden", response = Book.class)})
    @ApiParam(name = "id", type = "Integer", value = "ID des Buches")
    public Response getBook(@PathParam("id") Integer id) {
        Book bookById = this.bookRepository.getBookById(id);
        if (bookById == null) {
            return Response.status(404, "Book with id: " + id + " not found").build();
        } else {
            return Response.ok(bookById).build();
        }
    }

    @PUT
    @Path("{id}")
    @JsonView(GlobalViews.Detail.class)
    @ApiOperation(value = "Speichert ein einzelnes Buch anhand der ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "ok")})
    @ApiParam(name = "id", type = "Integer", value = "ID des Buches")
    @RolesAllowed("admin")
    public Response setBook(@PathParam("id") Integer id, Book book) {
        this.bookRepository.merge(book);
        return Response.ok().build();
    }
}
