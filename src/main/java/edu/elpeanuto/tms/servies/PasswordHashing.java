package edu.elpeanuto.tms.servies;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that hashes the password by SHA-1 algorithm
 */
public class PasswordHashing {
    public static String hashPassword(String password) {
        if(password == null){
            return null;
        }

        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = digest.digest(password.getBytes());

        return new String(bytes);
    }
}
