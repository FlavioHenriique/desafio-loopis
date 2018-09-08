package br.edu.ifpb.rest.resources;

import br.edu.ifpb.rest.dao.ProfessorDAO;
import br.edu.ifpb.rest.domain.Aluno;
import br.edu.ifpb.rest.domain.Professor;
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

@Path("professor")
public class ProfessorResource {

    private ProfessorDAO dao = new ProfessorDAO();
    private Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(String json) {

        dao.salvar(gson.fromJson(json, Professor.class));

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todos() {

        return Response
                .ok()
                .entity(gson.toJson(dao.todos()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{matricula}")
    public Response buscarProfessor(@PathParam("matricula") String matricula) {

        Professor p = dao.buscar(matricula);
        if (p != null) {
            return Response.ok().entity(gson.toJson(p)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/{mat}")
    public Response deletar(@PathParam("mat") String mat) {
        System.out.println(mat);
        dao.deletar(mat);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(String json) {
        Professor p = gson.fromJson(json, Professor.class);
       
        return Response
                .ok()
                .entity(gson.toJson(dao.atualizar(p)))
                .build();
    }
}

