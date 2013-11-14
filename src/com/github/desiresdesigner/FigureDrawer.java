package com.github.desiresdesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author desiresdesigner
 * @since 11/11/13
 */

class FigureDrawer extends Canvas implements MouseListener {

    private final Figure figure;
    private final int size;
    private JLabel infoXLabel;
    private JLabel infoYLabel;

    private Mark dotCoordinate;
    private Color markColor;
    private int diameter;

    private Mark lastDotCoordinate;
    private Color lastMarkColor;
    private int lastDiameter;

    public FigureDrawer(int size, Figure figure) {
        setBackground(Color.WHITE);
        this.size = size;
        this.figure = figure;
    }

    public void setLabels(JLabel xLabel, JLabel yLabel){
        infoXLabel = xLabel;
        infoYLabel = yLabel;
    }

    public void drawPoint(Mark p, Color color, int diameter){
        if (this.diameter == diameter)
            return;
        lastDotCoordinate = dotCoordinate;
        lastMarkColor = markColor;
        lastDiameter = this.diameter;
        dotCoordinate = new Mark(this.getWidth()/2 + p.getX(), this.getHeight()/2 - p.getY());
        markColor = color;
        this.diameter = diameter;
        repaint();
    }

    @Override
    public void update(Graphics g){
        if (diameter != 10 & lastDotCoordinate != null){
            if (lastMarkColor == Color.GREEN)
                g.setColor(Color.black);
            else
                g.setColor(Color.WHITE);
            g.fillOval((int)this.dotCoordinate.getX() - lastDiameter/2, (int)this.dotCoordinate.getY() - lastDiameter/2, lastDiameter, lastDiameter);
        } else if(diameter == 10) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            this.paint(g);
        }
        g.setColor(this.markColor);
        g.fillOval((int)this.dotCoordinate.getX() - diameter/2, (int)this.dotCoordinate.getY() - diameter/2, diameter, diameter);
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
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Mark p = new Mark(e.getX() - getWidth()/2, getHeight()/2 - e.getY());
        this.checkPoint(p);
    }

    public void checkPoint(Mark p){
        Color color = Color.RED;
        if (this.figure.contains(new Mark((int)(p.getX()/figure.scale), (int)(p.getY()/figure.scale)))){
            color = Color.GREEN;
        }
        Thread thread = new Thread(new PointCreationAnimator(this, color, p, figure.R/7));
        thread.start();
        infoXLabel.setText("x: " + String.valueOf((int)(p.getX()/figure.scale)));
        infoYLabel.setText("y: " + String.valueOf((int)(p.getY()/figure.scale)));
    }
}
