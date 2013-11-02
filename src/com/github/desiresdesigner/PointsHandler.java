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

class DrawingKeeper extends JFrame implements ActionListener{
    private Figure figure;
    private FigureDrawer figureDrawer;
    private JPanel panel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private int canvasSize;

    private JComboBox xComboBox;
    private JSpinner ySpinner;
    private JTextField RField;
    private JButton setDataButton;

    private JLabel infoXLabel;
    private JLabel infoYLabel;
    private JLabel infoActLabel;

    DrawingKeeper(int canvasSize, Figure figure, FigureDrawer figureDrawer){
        super("Definition Point In Field");
        this.figure = figure;
        this.figureDrawer = figureDrawer;

        this.canvasSize = canvasSize;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.preparePanel();
        this.getContentPane().add(panel);
    }

    private void preparePanel(){
        this.panel.setLayout(new BorderLayout());
        this.menuPanel.setLayout(new BoxLayout(this.menuPanel, BoxLayout.PAGE_AXIS));

        this.prepareControlPanel();
        this.prepareInfoPanel();
        this.setHandlers();

        this.menuPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.menuPanel.setMinimumSize(new Dimension(180, 300));
        this.menuPanel.setPreferredSize(new Dimension(180, 300));
        this.menuPanel.setMaximumSize(new Dimension(180, 300));
        this.panel.add(this.menuPanel, BorderLayout.LINE_START);
    }

    private void setHandlers(){

        this.setDataButton.addActionListener(this);

        /*this.xComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int xVal = ((Integer)cb.getSelectedItem()).intValue();

                System.out.println(xVal);
            }
        });

        this.setDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //this.xComboBox.getSelectedIndex();
                //Mark point = new Mark(this)
                System.out.println("You clicked the button");
            }
        });*/
    }

    private void prepareControlComponents(){
        this.xComboBox = new JComboBox();

        String[] yCoord = new String[this.canvasSize];
        for (int i = 0; i < this.canvasSize; i++) {
            this.xComboBox.addItem (i - this.canvasSize / 2);
            yCoord[i] = String.valueOf(i - this.canvasSize / 2);
        }
        SpinnerModel ySpinnerModel = new SpinnerListModel(yCoord);
        this.ySpinner = new JSpinner(ySpinnerModel);

        this.RField = new JTextField();

        RField.setText("RField");
        this.setDataButton = new JButton("Set Data");
    }

    private void setMark(Mark p){
        if (this.figure.contains(p)){
            System.out.println(p.getX() + " - " + p.getY() + " - contains");
            this.figureDrawer.drawPoint(p, Color.GREEN);
            return;
        }
        System.out.println(p.getX() + " - " + p.getY() + " - NOT contains");
        this.figureDrawer.drawPoint(p, Color.RED);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Set Data"))
        {
            int x = ((Integer)this.xComboBox.getSelectedItem()).intValue();
            int y = Integer.parseInt((String)this.ySpinner.getValue());
            System.out.println("Listener thinks, that: " + x + " - " + y);
            this.setMark(new Mark(x, y));
            //this.infoXLabel.setText("x: 0");
        }
    }

    private void prepareControlPanel(){
        this.prepareControlComponents();
        JLabel labelX = new JLabel("Set x: ");
        JLabel labelY = new JLabel("Set y: ");
        JLabel labelR = new JLabel("Set R: ");

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Control"));
        JPanel setPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setPanel.setLayout(new GridLayout(0, 2, 0, 0));
        controlPanel.setPreferredSize(new Dimension(150, 150));
        controlPanel.setMaximumSize(new Dimension(150, 150));
        setPanel.add(labelX);
        setPanel.add(this.xComboBox);
        setPanel.add(labelY);
        setPanel.add(this.ySpinner);
        setPanel.add(labelR);
        setPanel.add(this.RField);
        controlPanel.add(setPanel);
        controlPanel.add(this.setDataButton);

        this.menuPanel.add(controlPanel);
    }

    private void prepareInfoComponents(){
        this.infoXLabel = new JLabel("x: NULL");
        this.infoYLabel = new JLabel("y: NULL");
        this.infoActLabel = new JLabel("<html>Hello, user! <br> This is an interactive program.</html>");
        infoActLabel.setMinimumSize(new Dimension(140, 300));
        infoActLabel.setMaximumSize(new Dimension(140, 300));
    }

    private void prepareInfoPanel(){
        this.prepareInfoComponents();
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Information"));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(this.infoXLabel);
        infoPanel.add(this.infoYLabel);
        infoPanel.add(this.infoActLabel);

        this.menuPanel.add(infoPanel);
    }

    public void addCanvas(Canvas canvas){
        this.panel.add(canvas, BorderLayout.CENTER);
    }
}

class FigureDrawer extends Canvas{

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
        System.out.println("Canvas thinks, that: " + this.dotCoords.getX() + " - " + this.dotCoords.getY());
        this.mark_color = color;
        this.repaint();
    }

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

        System.out.println(getWidth() + " - " + getHeight());
        System.out.println(centerWidth + " - " + centerHeight);

        g.drawLine(0, centerHeight, getWidth(), centerHeight);
        g.drawLine(centerWidth, 0, centerWidth, getHeight());

        final Mark center = new Mark(centerWidth, centerHeight);
        figure.draw(g, center);
    }
}