package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/28/13
 */

import java.awt.*;
import java.awt.event.*;
//import java.awt.event.WindowEvent;
//import com.sun.java.swing.*;

import javax.swing.*;


class PointsHandler{
    DrawingKeeper f;

    PointsHandler(){
        this.f = new DrawingKeeper();

        this.f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.f.setSize(300, 300);
        this.f.add(new FigureDrawer());
        this.f.setVisible(true);
    }
}

class DrawingKeeper extends JFrame{
    DrawingKeeper(){
        super("Definition Point In Field");
    }
}

class FigureDrawer extends Canvas{

    public void paint(Graphics g){
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
    }
}