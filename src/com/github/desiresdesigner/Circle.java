package com.github.desiresdesigner;

import java.awt.*;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */

public class Circle extends AbstractQuadrant {
    private double r; // радиус окружности

    public Circle(double r, int number, double scale){
        this.number = number;
        this.scale = scale;
        if (this.number == 2 || this.number == 3)
            this.ox = -r;
        else
            this.ox = r;
        if (this.number < 3)
            this.oy = r;
        else
            this.oy = -r;
        this.r = r;
    }

    @Override
    public boolean contains(Mark p){
        return Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2)) < this.r;
    }

    @Override
    public void draw(Graphics g, Mark center){
        int startAngle = 0;
        int endAngle = 90;
        if (this.number == 2){
            startAngle = 90;
        } else if (this.number == 3){
            startAngle = 180;
        } else if (this.number == 4){
            endAngle = -90;
        }
        g.fillArc((int)(center.getX() - r), (int)(center.getY() - r), (int)(2*r), (int) (2*r), startAngle, endAngle);
    }
}
