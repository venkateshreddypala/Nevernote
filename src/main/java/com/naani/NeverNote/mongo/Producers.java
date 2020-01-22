package com.naani.NeverNote.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.Produces;

@RequestScoped
public class Producers{
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoClient getMongoClient(){
        if(mongoClient == null){
            String host = System.getProperty("mongo.host","localhost");
            String port = System.getProperty("mongo.port", "27017");
            mongoClient = new MongoClient(host, Integer.parseInt(port));

        }
        return mongoClient;
    }
    @Produces
    private MongoDatabase getDatabase() {
        if (database == null) {
            database = getMongoClient().getDatabase("notedb");
        }

        return database;
    }

    @Produces
    @Collection
    public MongoCollection<Document> getCollection(InjectionPoint injectionPoint) {
        Collection mc = injectionPoint.getAnnotated().getAnnotation(Collection.class);
        return getDatabase().getCollection(mc.value());
    }
}



