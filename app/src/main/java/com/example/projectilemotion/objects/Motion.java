package com.example.projectilemotion.objects;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Motion {

    public ArrayList<Point> resultList = new ArrayList<Point>();

    public void motionCalculation(int velocity, int angle) {
        double vx = velocity * cos(toRadians(angle));
        double vy = velocity * sin(toRadians(angle));
        double g = 9.81;
        double TofFlight = 2 * vy / g;
        double x = 0, y = 0;
        double step = 0.1;
        for (double t = 0; t < TofFlight; t = t + step) {
            x = velocity * t * cos(toRadians(angle));
            y = velocity * t * sin(toRadians(angle)) - 0.5 * g * t * t;
            resultList.add(new Point(x, y));
        }
    }
}
