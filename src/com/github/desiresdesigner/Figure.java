package com.github.desiresdesigner;

/**
 * @author desiresdesigner, trionia
 * @since 10/15/13
 */
public class Figure {
    private Quadrant q1;
    private Quadrant q2;
    private Quadrant q3;
    private Quadrant q4;

    public Figure(int R){
        this.q1 = new Triangle(R, R, 1);
        this.q2 = new Quadrant(2);
        this.q3 = new Rectangle(-R, -R /2, 3);
        this.q4 = new Circle(R, 4);
    }

    public boolean check_enter(Mark p){
        if (p.y == 0){
            double min = q2.getOx() > q3.getOx() ? q2.getOx() : q3.getOx();
            double max = q1.getOx() < q4.getOx() ? q1.getOx() : q4.getOx();
            return p.x > min && p.x < max;
        }
        if (p.x == 0){
            double min = q3.getOy() < q4.getOy() ? q4.getOy() : q3.getOy();
            double max = q1.getOy() > q2.getOy() ? q2.getOy() : q1.getOy();
            return p.y > min && p.y < max;
        }
        if (p.x > 0 && p.y > 0){
            return this.q1.contains(p);
        } else if (p.x < 0 && p.y > 0) {
            return this.q2.contains(p);
        } else if (p.x < 0 && p.y < 0) {
            return this.q3.contains(p);
        } else if (p.x > 0 && p.y < 0) {
            return this.q4.contains(p);
        }
        return false;
    }
}
