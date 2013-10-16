package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */
public class Circle extends Quadrant {
    private double r; // радиус окружности

    public Circle(double r, int number){
        this.number = number;
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

    public boolean contains(Mark p){
        return Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)) < this.r;
    }
}
