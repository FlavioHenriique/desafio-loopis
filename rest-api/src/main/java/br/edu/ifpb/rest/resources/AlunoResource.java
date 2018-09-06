package br.edu.ifpb.rest.resources;

import br.edu.ifpb.rest.dao.AlunoDAO;
import br.edu.ifpb.rest.domain.Aluno;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("aluno")
public class AlunoResource {

    private AlunoDAO dao = new AlunoDAO();
    private Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(String json) {

        dao.salvar(gson.fromJson(json, Aluno.class));
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todos() {
        System.out.println("aui");
        return Response
                .ok()
                .entity(gson.toJson(dao.todos()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{matricula}")
    public Response buscarAluno(@PathParam("matricula") String matricula) {

        Aluno aluno = dao.buscar(matricula);
        if (aluno != null) {
            return Response.ok().entity(gson.toJson(aluno)).build();
        }
        return  Response.status(Status.NOT_FOUND).build();
    }
}
