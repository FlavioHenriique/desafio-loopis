package br.edu.ifpb.rest.resources;

import br.edu.ifpb.rest.dao.DisciplinaDAO;
import br.edu.ifpb.rest.domain.Disciplina;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("disciplina")
public class DisciplinaResource {

    private DisciplinaDAO dao = new DisciplinaDAO();
    private Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(String json) {

        Disciplina d = gson.fromJson(json, Disciplina.class);
        dao.salvar(d);

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarDisciplina(@PathParam("id") int id) {

        Disciplina d = dao.buscar(id);
        if (d != null) {
            return Response
                    .ok()
                    .entity(gson.toJson(d))
                    .build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todas() {

        String json = gson.toJson(dao.todas());

        return Response.ok().entity(json).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {

        dao.deletar(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(String json) {

        Disciplina d = dao.atualizar(
                gson.fromJson(json, Disciplina.class)
        );

        return Response.ok().entity(gson.toJson(d)).build();

    }
}
