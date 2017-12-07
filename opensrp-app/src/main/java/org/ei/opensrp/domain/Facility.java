package org.ei.opensrp.drishti.DataModels;

import java.util.ArrayList;

/**
 * Created by ali on 9/5/17.
 */

public class Facility  {
    private String name, id;

    public Facility() {

    }

    public Facility(String id, String name) {
        this.id = id;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Facility> createFacilityList() {
        ArrayList<Facility> visitedMom = new ArrayList<Facility>();


        visitedMom.add(new Facility("0001","facility A"));
        visitedMom.add(new Facility("0002","facility B"));
        visitedMom.add(new Facility("0003","facility C"));
        visitedMom.add(new Facility("0004","facility D"));
        visitedMom.add(new Facility("0005","facility E"));
        visitedMom.add(new Facility("0007","facility F"));


        return visitedMom;
    }
}