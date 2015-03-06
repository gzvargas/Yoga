package com.example.zachvargas.yoga.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zach Vargas on 3/6/2015.
 */
public class Routine {

    public int backendId;

    public Date created_at;
    public Date updated_at;

    public String name;
    public int difficulty;

    public ArrayList<Pose> poses;

    public Routine() {
        name = Integer.toString(backendId);
        difficulty = 1;
    }
}
