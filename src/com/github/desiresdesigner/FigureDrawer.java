package com.github.desiresdesigner;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: desiresdesigner
 * Date: 11/11/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
class FigureDrawer extends Canvas {

    private final Figure figure;
    private final int size;

    private Mark dotCoords;
    private Color mark_color;

    public FigureDrawer(int size, Figure figure) {
        this.size = size;
        this.figure = figure;
    }

    public void drawPoint(Mark p, Color color){
        this.dotCoords = new Mark(this.getWidth()/2 + p.getX(), this.getHeight()/2 - p.getY());
        this.mark_color = color;
        this.repaint();
    }

    @Override
    public void update(Graphics g){
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.paint(g);
        g.setColor(this.mark_color);
        g.fillOval((int)this.dotCoords.getX() - 5, (int)this.dotCoords.getY() - 5, 10, 10);
    }

    @Override
    public void paint(Graphics g) {
        this.setSize(this.size, this.size);

        final int centerHeight = getHeight() / 2;
        final int centerWidth = getWidth() / 2;

        g.drawLine(0, centerHeight, getWidth(), centerHeight);
        g.drawLine(centerWidth, 0, centerWidth, getHeight());

        final Mark center = new Mark(centerWidth, centerHeight);
        figure.draw(g, center);
    }
}
