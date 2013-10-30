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
    private final AbstractQuadrant q1;
    private final AbstractQuadrant q2;
    private final AbstractQuadrant q3;
    private final AbstractQuadrant q4;

    public Figure(int R){
        /*this.q1 = new Triangle(R, R, 3);
        this.q2 = new EmptyQuadrant(2);
        this.q3 = new Rectangle(-R, -R /2, 3);
        this.q4 = new Circle(R, 4);*/
        this.q1 = new Rectangle(R / 2, R, 1);
        this.q2 = new Triangle(-R / 2, -R / 2, 2);
        this.q3 = new EmptyQuadrant(3);
        this.q4 = new Circle(R / 2, 4);
    }

    public boolean contains(Mark p){
        if (p.getY() == 0){
            double min = q2.getOx() > q3.getOx() ? q2.getOx() : q3.getOx();
            double max = q1.getOx() < q4.getOx() ? q1.getOx() : q4.getOx();
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

    @Override
    public void draw(Graphics g, Mark center) {
        for (Drawable drawable: getQuadrants()){
            drawable.draw(g, center);
        }
        /*for (AbstractQuadrant AbsQua: getQuadrants()){
            AbsQua.draw(g, center);
        }*/
    }
}
