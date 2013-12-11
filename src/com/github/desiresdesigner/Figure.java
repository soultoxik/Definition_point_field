package com.github.desiresdesigner;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author desiresdesigner, trionia
 * @since 10/15/13
 */

public class Figure implements Drawable {
    private AbstractQuadrant q1;
    private AbstractQuadrant q2;
    private AbstractQuadrant q3;
    private AbstractQuadrant q4;
    int R;
    double scale;

    public Figure(int R){
        this.R = R;
        scale = this.R/R;
        this.q1 = new Rectangle(R / 2, R, 1, scale);
        this.q2 = new Triangle(-R / 2, R / 2, 2, scale);
        this.q3 = new EmptyQuadrant(3);
        this.q4 = new Circle(R / 2, 4, scale);
    }

    void setQuadrants(double scale){
        q1.setScale(scale);
        q2.setScale(scale);
        q3.setScale(scale);
        q4.setScale(scale);
    }

    public boolean contains(Mark p){
        if (p.getY() == 0){
            double min = q2.getOx() > q3.getOx() ? q2.getOx() : q3.getOx();
            double max = q1.getOx() < q4.getOx() ? q4.getOx() : q1.getOx();
            return p.getX() > min && p.getX() < max;
        }
        if (p.getX() == 0){
            double min = q3.getOy() < q4.getOy() ? q4.getOy() : q3.getOy();
            double max = q1.getOy() > q2.getOy() ? q2.getOy() : q1.getOy();
            return p.getY() > min && p.getY() < max;
        }
        if (p.getX() > 0 && p.getY() > 0){
            return this.q1.contains(p);
        } else if (p.getX() < 0 && p.getY() > 0) {
            return this.q2.contains(p);
        } else if (p.getX() < 0 && p.getY() < 0) {
            return this.q3.contains(p);
        } else if (p.getX() > 0 && p.getY() < 0) {
            return this.q4.contains(p);
        }
        return false;
    }

    Set<AbstractQuadrant> getQuadrants(){
        return new HashSet<AbstractQuadrant>(Arrays.asList(q1, q2, q3, q4));
    }

    void setR(int R){
        scale = this.R/R;
        setQuadrants(scale);
    }

    @Override
    public void draw(Graphics g, Mark center) {
        for (Drawable drawable: getQuadrants()){
            drawable.draw(g, center);
        }
    }
}
