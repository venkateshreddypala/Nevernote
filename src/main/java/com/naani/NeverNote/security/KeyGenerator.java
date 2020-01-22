package com.naani.NeverNote.security;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Singleton;
import java.security.Key;

@Singleton
public class KeyGenerator {
    private Key key;
    public Key getKey() {
        if (key == null) {
            String keyString = System.getProperty("signing.key", "replace for production");
            key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        }

        return key;
    }
}
