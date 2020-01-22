package com.naani.NeverNote.rest.resource;


import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import com.naani.NeverNote.model.Note;
import com.naani.NeverNote.model.User;
import com.naani.NeverNote.mongo.Collection;
import com.naani.NeverNote.security.Secure;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This application will have a simple API with the primary endpoint begin to interact with notes.
 * This class will have appropriate JAX-RS annotations
 */

/**
 * This class describes endpoint at /api/notes and will produce JSON results
 */




@Path("/notes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Secure
public class NoteResource {

    @Inject
    private User user;
    @Inject
    @Collection("notes")
    private MongoCollection<Document> collection;

    @Context
    private UriInfo uriInfo;

    @PostConstruct
    public void postConstruct() {
        String host = System.getProperty("mongo.host", "localhost");
        String port = System.getProperty("mongo.port", "27017");
    }

    @GET
    public Response getAll() {
        List<Note> notes = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find(new BasicDBObject("user_id", user.getId())).iterator()) {
            while (cursor.hasNext()) {
                notes.add(new Note(cursor.next()));
            }
        }

        return Response.ok(new GenericEntity<List<Note>>(notes) {
        }).build();
    }

    @GET
    @Path("{id}")
    public Response getNote(@PathParam("id") String id) {
        Document doc = collection.find(buildQueryById(id)).first();
        if (doc == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(new Note(doc)).build();
        }
    }

    @POST
    public Response createNote(Note note) {
        Document doc = note.toDocument();
        doc.append("user_id", user.getId());
        collection.insertOne(doc);
        final String id = doc.get("_id", ObjectId.class).toHexString();

        return Response.created(uriInfo.getRequestUriBuilder().path(id).build()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateNote(Note note) {
        note.setModified(LocalDateTime.now());
        note.setUser(user.getId());
        UpdateResult result = collection.updateOne(buildQueryById(note.getId()),
                new Document("$set", note.toDocument()));
        if (result.getModifiedCount() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteNote(@PathParam("id") String id) {
        collection.deleteOne(buildQueryById(id));
        return Response.ok().build();
    }

    private BasicDBObject buildQueryById(String id) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id)).append("user_id", user.getId());
        return query;
    }
}
