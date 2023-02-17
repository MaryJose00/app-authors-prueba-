package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar",description = "Busca segun el id")
    @APIResponse(responseCode = "200",description = "Correcto",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Authors.class)))
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @Inject AuthorRepository repository;

    @GET
    @Operation(summary = "Buscar",description = "Lista todos")
    @APIResponse(responseCode = "200",description = "Correcto",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Authors.class)))
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    @Operation(summary = "Insertar",description = "Crea un nuevo registro")
    @APIResponse(responseCode = "200",description = "Correcto",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Authors.class)))
    public void insert(Authors obj) {
        repository.persist(obj);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar",description = "Modifica un elemento segun el id")
    @APIResponse(responseCode = "200",description = "Correcto",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Authors.class)))
    public void update(Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);

        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar",description = "Elimina elementos por el id")
    @APIResponse(responseCode = "200",description = "Correcto",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Authors.class)))
    public void delete( @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
