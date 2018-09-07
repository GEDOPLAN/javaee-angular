
import javax.ejb.EJBAccessException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Provider
public class EJBAccessExceptionMapper implements ExceptionMapper<javax.ejb.EJBAccessException>{

    @Override
    public Response toResponse(EJBAccessException exception) {
        return Response.status(403, "Benutzer für diese Aktion nicht zugelassen").build();
    }

}
