package com.example.zachvargas.yoga.Model;

import java.util.*;

/**
 * Created by Zach Vargas on 3/6/2015.
 */
public class Pose {
    public int backendId;

    public Date created_at;
    public Date updated_at;

    public String name;
    public String image_url;

    public int duration;
    public int difficulty;
    public boolean dailyPose;


    //No-args constructor required by GSON
    public Pose() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\t id: " + backendId);
        sb.append("\n\t createdAt: " + created_at.toString());
        sb.append("\n\t updatedAt: " + updated_at.toString());
        sb.append("\n\t name: " + name);
        sb.append("\n\t duration: " + duration);
        sb.append("\n\t difficulty: " + difficulty);
        sb.append("\n\t image_url: " + image_url);
        sb.append("\n\t dailyPose: " + dailyPose);

        return sb.toString();
    }
}
