package com.naani.NeverNote.rest;


import com.naani.NeverNote.rest.resource.AuthenticationResource;
import com.naani.NeverNote.rest.resource.NoteResource;
import com.naani.NeverNote.security.SecureFilter;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

// @Application specifies the root URL of my application REST endpoints
@ApplicationPath("/api")
public class Nevernote extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> s = new HashSet<>();
        s.add(NoteResource.class);
        s.add(SecureFilter.class);
        s.add(AuthenticationResource.class);
        return s;
    }

}
