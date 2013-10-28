package com.github.desiresdesigner;

/**
 * @author desiresdesigner, trionia
 * @since 10/15/13
 */
public class Figure {
    static Quadrant q1;
    static Quadrant q2;
    static Quadrant q3;
    static Quadrant q4;

    public Figure(int R){
        this.q1 = new Triangle(R, R, 1);
        this.q2 = new Quadrant(2);
        this.q3 = new Rectangle(-R, -R /2, 3);
        this.q4 = new Circle(R, 4);
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

    Mark getUpperPoint(){
        return new Mark(0, 0);
    }

    Mark getNext(Mark m){
        return m;
    }
}
