package com.github.desiresdesigner;

import java.awt.*;

/**
 * @author desiresdesigner
 * @since 10/15/13
 */

public class Rectangle extends AbstractQuadrant {
    public Rectangle(double x, double y, int number, double scale){
        this.scale = scale;
        this.ox = x;
        this.oy = y;
        this.number = number;
    }

    public boolean contains(Mark p){
        switch (this.number){
            case 1:
                return p.getX() < this.ox && p.getY() < this.oy;
            case 2:
                return p.getX() > this.ox && p.getY() < this.oy;
            case 3:
                return p.getX() > this.ox && p.getY() > this.oy;
            case 4:
                return p.getX() < this.ox && p.getY() > this.oy;
            default:
                return false;
        }

    }

  @Override
  public void draw(Graphics g, Mark center){
      int[] xPoints = {(int)(center.getX()), (int)(center.getX() + ox), (int)(center.getX() + ox), (int)(center.getX())};
      int[] yPoints = {(int)(center.getY()), (int)(center.getY()), (int)(center.getY() - oy), (int)(center.getY() - oy)};
      g.fillPolygon(xPoints, yPoints, 4);
  }
}
