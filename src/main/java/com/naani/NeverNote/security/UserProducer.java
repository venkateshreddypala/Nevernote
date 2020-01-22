package com.naani.NeverNote.security;


import com.google.common.net.HttpHeaders;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.naani.NeverNote.model.User;
import com.naani.NeverNote.mongo.Collection;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.bson.Document;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class UserProducer {

    @Inject
    private KeyGenerator keyGenerator;
    @Inject
    HttpServletRequest req;
    @Inject
    @Collection("users")
    private MongoCollection<Document> users;

    @Produces
    public User getUser() {
        String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.contains("Bearer")) {
            String token = authHeader.substring("Bearer".length()).trim();
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(keyGenerator.getKey()).parseClaimsJws(token);
            return getUser(parseClaimsJws.getBody().getSubject());
        } else {
            return null;
        }
    }

    private User getUser(String email) {
        Document doc = users.find(new BasicDBObject("email", email)).first();
        if (doc != null) {
            return new User(doc);
        } else {
            return null;
        }
    }

}
