package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */
public class Quadrant {
    protected int number;
    protected double ox = 0;//пересечение с ОХ
    protected double oy = 0;//пересечение с ОY

    public Quadrant (){
    }

    public Quadrant (int n){
        this.number = n;
    }

    public boolean contains(Mark p){
        return p.getX() == 0 && p.getY() == 0;
    }

    public double getOx(){
        return this.ox;
    }

    public double getOy(){
        return this.oy;
    }
}
