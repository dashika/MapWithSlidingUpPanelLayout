package com.example.dashika.fuelbuddy;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by dashika on 30/05/17.
 */

public class Element {

    private float coast;
    private long time;
    private String icon;
    private String name;
    private String address;
    private float distance;
    private LatLng location;

    public Element(float coast,
                   long time,
                   String icon,
                   String name,
                   String address,
                   float distance,
                   LatLng location) {
        this.coast = coast;
        this.time = time;
        this.icon = icon;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.location = location;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
