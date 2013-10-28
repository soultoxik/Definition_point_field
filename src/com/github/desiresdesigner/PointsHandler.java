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
    private final DrawingKeeper drawingKeeper;
    private final Figure figure;

  PointsHandler() {
        this.drawingKeeper = new DrawingKeeper();
        this.drawingKeeper.setSize(300, 300);
        figure = new Figure(50);
        final FigureDrawer figureDrawer = new FigureDrawer(drawingKeeper, figure);

        this.drawingKeeper.add(figureDrawer);
        this.drawingKeeper.setVisible(true);
    }
}

class DrawingKeeper extends JFrame{
    DrawingKeeper(){
        super("Definition Point In Field");
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e)
        {
          System.exit(0);
        }
      });
    }
}

class FigureDrawer extends Canvas{

  private DrawingKeeper drawingKeeper;
  private final Figure figure;

  public FigureDrawer(DrawingKeeper drawingKeeper, Figure figure) {
    this.drawingKeeper = drawingKeeper;
    this.figure = figure;
  }

  @Override
    public void paint(Graphics g) {
    final int centerHeight = getHeight() / 2;
    final int centerWidth = getWidth() / 2;
    g.drawLine(0, centerHeight, getWidth(), centerHeight);
    g.drawLine(centerWidth, 0, centerWidth, getHeight());
    final Mark center = new Mark(centerHeight, centerWidth);
    figure.draw(g, center);
    }
}