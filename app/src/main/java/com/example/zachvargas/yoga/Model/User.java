package com.example.zachvargas.yoga.Model;

import java.util.*;
/**
 * Created by Zach Vargas on 3/6/2015.
 */
public class User {
    public int backendId;

    public Date created_at;
    public Date updated_at;

    public String name;
    public String email;

    public String authToken;
    public Date tokenExpiration;

    public User() {
        name = "";
        email = "";
        created_at = new Date();
        updated_at = new Date();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\t id: " + backendId);
        sb.append("\n\t name: " + name);
        sb.append("\n\t email: " + email);
        sb.append("\n\t authToken: " + authToken);
        sb.append("\n\t tokenExpiration: " + tokenExpiration.toString());

        return sb.toString();
    }
}