package br.edu.ifpb.rest.resources;

import br.edu.ifpb.rest.dao.TurmaDAO;
import br.edu.ifpb.rest.domain.Turma;
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

@Path("turma")
public class TurmaResource {

    private TurmaDAO dao = new TurmaDAO();
    private Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(String json) {

        Turma d = gson.fromJson(json, Turma.class);
        dao.salvar(d);

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarDisciplina(@PathParam("id") int id) {

        Turma d = dao.buscar(id);
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

        Turma d = dao.atualizar(gson.fromJson(json, Turma.class)
        );

        return Response.ok().entity(gson.toJson(d)).build();

    }
}
