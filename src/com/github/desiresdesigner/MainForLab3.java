package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 11/14/13
 */

import java.util.LinkedHashSet;
import java.util.Set;

public class MainForLab3 {
    public static void main(String[] args) {
        Set<Mark> points = getMarks();

        int r;
        try{
            r = getROrDie(args);
        } catch (Exception e){
            System.err.println(e.getMessage());
            return;
        }

        Figure f = new Figure(r);
        printContainingPoints(points, f);
    }

    public static void mainWithException(String[] args) {
        Set<Mark> points = getMarks();
        int r = getROrDie(args);
        Figure f = new Figure(r);
        printContainingPoints(points, f);
    }

    private static Set<Mark> getMarks() {
        Set<Mark> points = new LinkedHashSet<>();
        points.add(new Mark(4, 3));
        points.add(new Mark(2, -2));
        points.add(new Mark(5, -3));
        points.add(new Mark(0, 2));
        points.add(new Mark(-5, -5));
        points.add(new Mark(-2, -1));
        points.add(new Mark(-4, 4));
        return points;
    }

    private static void printContainingPoints(Set<Mark> points, Figure f) {
        for (Mark point : points){
            if(f.contains(point)){
                System.out.println("Point (" + point.getX() + ", " + point.getY() + ") is in specified area");
            }
        }
    }

    private static int getROrDie(String[] args) {
        int r = 4;
        if (args.length != 0){
            try {
                r = Integer.parseInt(args[0]);
                if (r <= 0)
                    throw new IllegalArgumentException();
            } catch(Exception e){
                throw new IllegalArgumentException("Wrong Input, R must be an Integer and more than 0", e);
            }
        }
        return r;
    }
}
