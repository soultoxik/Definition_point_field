package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */
public class Rectangle extends Quadrant {
    //private double ox; //пересечение с ОХ
    //private double oy; //пересечение с ОУ

    public Rectangle(double x, double y, int number){
        this.ox = x;
        this.oy = y;
        this.number = number;
    }

    public boolean contains(Mark p){
        switch (this.number){
            case 1:
                return p.x < this.ox && p.y < this.oy;
            case 2:
                return p.x > this.ox && p.y < this.oy;
            case 3:
                return p.x > this.ox && p.y > this.oy;
            case 4:
                return p.x < this.ox && p.y > this.oy;
            default:
                return false;
        }

    }
}
