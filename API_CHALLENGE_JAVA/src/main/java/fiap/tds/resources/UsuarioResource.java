package fiap.tds.resources;

import fiap.tds.models.Usuario;
import fiap.tds.repositories.UsuarioRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("cadastro")
public class UsuarioResource {
    private final UsuarioRepository usuarioRepository;

    public UsuarioResource(){
        usuarioRepository = new UsuarioRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios(){
        return usuarioRepository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("id") int id){
       return usuarioRepository.getById(id).orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuario){
        usuarioRepository.cadastrar(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id") int id, Usuario usuario){
        usuarioRepository.atualizar(id, usuario);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        usuarioRepository.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
