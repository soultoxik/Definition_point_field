package com.github.desiresdesigner;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) {
        LinkedHashSet<Mark> points = new LinkedHashSet<Mark>();
        points.add(new Mark(4, 3));
        points.add(new Mark(2, -2));
        points.add(new Mark(5, -3));
        points.add(new Mark(0, 2));
        points.add(new Mark(-5, -5));
        points.add(new Mark(-2, -1));
        points.add(new Mark(-4, 4));

        int r = 4;
        if (args.length != 0)
            r = Integer.parseInt(args[0]);
        Figure f = new Figure(r);

        for (Mark point : points){
            if(f.check_enter(point)){
                System.out.println("Point (" + point.x + ", " + point.y + ") is in specified area");
            }
        }
    }
}
