package com.github.desiresdesigner;

/**
 * @author desiresdesigner
 * @since 10/28/13
 */

class FrameContainer {

    FrameContainer() {
        Figure figure = new Figure(120);
        int canvasRadius = 300;
        final FigureDrawer figureDrawer = new FigureDrawer(canvasRadius, figure);
        DrawingKeeper drawingKeeper = new DrawingKeeper(canvasRadius, figure, figureDrawer);
        int[] frameSize = {500, 300};
        drawingKeeper.setSize(frameSize[0], frameSize[1]);
        drawingKeeper.addCanvas(figureDrawer);
        drawingKeeper.setVisible(true);
    }
}