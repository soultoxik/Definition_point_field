package com.github.desiresdesigner;

import java.awt.*;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */
public class Triangle extends AbstractQuadrant {
    //private double ox; //пересечение с ОХ
    //private double oy; //пересечение с ОУ

    public Triangle(double x, double y, int number){
        this.number = number;

        this.ox = x;
        this.oy = y;

    }

    public boolean contains(Mark p){
        if (this.number < 3){
            return p.getY() < (-1)*(this.oy/this.ox)*p.getX() + this.oy;
        }
        return p.getY() > (-1)*(this.oy/this.ox)*p.getX() + this.oy;
    }

  @Override
  public void draw(Graphics g, Mark center) {
    g.drawLine((int) ox, 0, 0, (int) oy);
  }
}
