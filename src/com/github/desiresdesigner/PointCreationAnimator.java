package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 11/11/13
 */

import java.awt.*;

public class PointCreationAnimator implements Runnable {
    private final FigureDrawer figureDrawer;
    private final Color markColor;
    private final Mark mark;
    private int diameter;

    public PointCreationAnimator(FigureDrawer figureDrawer, Color color, Mark mark, int diameter) {
        this.figureDrawer = figureDrawer;
        markColor = color;
        this.mark = mark;
        this.diameter = diameter > 10 ? diameter : 15;
        //this.diameter = 50;
    }

    @Override
    public void run() {
        while (diameter > 9){
            figureDrawer.drawPoint(mark, markColor, diameter);
            diameter -= 1;
            //diameter -= 5;
            try{
                Thread.sleep((long)100);
            }
            catch(Exception e){
                System.out.println("OLOLO");
            }
        }
    }
}
