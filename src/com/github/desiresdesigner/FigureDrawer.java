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
    private Color markColor;
    private int diameter;

    private Mark lastDotCoords;
    private Color lastMarkColor;
    private int lastDiameter;
    private int lastR;

    public FigureDrawer(int size, Figure figure) {
        setBackground(Color.WHITE);
        this.size = size;
        this.figure = figure;
    }

    public void drawPoint(Mark p, Color color, int diameter){
        lastDotCoords = dotCoords;
        lastMarkColor = markColor;
        lastDiameter = this.diameter;
        dotCoords = new Mark(this.getWidth()/2 + p.getX(), this.getHeight()/2 - p.getY());
        markColor = color;
        this.diameter = diameter;
        repaint();
    }

    @Override
    public void update(Graphics g){
        if (lastR != figure.R){
            lastR = figure.R;
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            this.paint(g);
        }
        if (diameter != 10 & lastDotCoords != null){
            if (lastMarkColor == Color.GREEN)
                g.setColor(Color.black);
            else
                g.setColor(Color.WHITE);
            g.fillOval((int)this.dotCoords.getX() - lastDiameter/2, (int)this.dotCoords.getY() - lastDiameter/2, lastDiameter, lastDiameter);
        } else if(diameter == 10) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            this.paint(g);
        }
        g.setColor(this.markColor);
        g.fillOval((int)this.dotCoords.getX() - diameter/2, (int)this.dotCoords.getY() - diameter/2, diameter, diameter);
    }

    @Override
    public void paint(Graphics g) {
        this.setSize(this.size, this.size);

        final int centerHeight = getHeight() / 2;
        final int centerWidth = getWidth() / 2;

        g.setColor(Color.black);
        g.drawLine(0, centerHeight, getWidth(), centerHeight);
        g.drawLine(centerWidth, 0, centerWidth, getHeight());

        final Mark center = new Mark(centerWidth, centerHeight);
        figure.draw(g, center);
    }
}
