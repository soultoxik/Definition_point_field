package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/28/13
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
//import javax.swing.border.TitledBorder;


class PointsHandler{
    private final DrawingKeeper drawingKeeper;
    private final Figure figure;
    private int[] frameSize = {500, 300};
    private int canvasRadious = 300;

    PointsHandler() {
        figure = new Figure(120);
        final FigureDrawer figureDrawer = new FigureDrawer(this.canvasRadious, figure);
        this.drawingKeeper = new DrawingKeeper(this.canvasRadious, this.figure, figureDrawer);
        this.drawingKeeper.setSize(this.frameSize[0], this.frameSize[1]);
        this.drawingKeeper.addCanvas(figureDrawer);
        this.drawingKeeper.setVisible(true);
    }
}