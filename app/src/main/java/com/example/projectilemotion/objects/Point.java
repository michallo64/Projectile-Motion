package com.example.projectilemotion.objects;

import java.io.Serializable;

public class Point implements Serializable {
    public double x;
    public double y;
    public double time;

    public Point(){

    }
    public Point(double x, double y, double time){
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
