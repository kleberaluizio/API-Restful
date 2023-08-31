package br.com.treinaweb;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pessoa")
public class PessoaResource {

    private PessoaRepository _repository = new PessoaRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> get(){
        return _repository.GetAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa getById(@PathParam("id") int id){
        return _repository.Get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Pessoa pessoa){

        try {
            _repository.Add(pessoa);
            return Response.status(Response.Status.CREATED).entity(pessoa).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, Pessoa pessoa){
        Pessoa p = _repository.Get(id);

        if(p == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try{
            pessoa.setId(id);
            _repository.Edit(pessoa);
            return Response.status(Response.Status.OK).entity(pessoa).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id){
        Pessoa p = _repository.Get(id);

        if(p == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try{
            _repository.Delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

}

