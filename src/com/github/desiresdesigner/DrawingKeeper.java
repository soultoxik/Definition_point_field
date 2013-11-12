package com.github.desiresdesigner;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: desiresdesigner
 * Date: 11/11/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
class DrawingKeeper extends JFrame implements ActionListener { //, MouseListener {
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
        //this.addMouseListener(this);
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
        Color color = Color.RED;
        if (this.figure.contains(p)){
            color = Color.GREEN;
        }
        Thread thread = new Thread(new PointCreationAnimator(figureDrawer, color, p, this.figure.R/7));
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Set Data"))
        {
            try {
                int R = Integer.parseInt(RField.getText());
                if (R == 0)
                    throw new NumberFormatException();
                figure.setR(R);
                infoActLabel.setText("<html>Win!</html>");
            }
            catch (NumberFormatException e1) {
                infoActLabel.setText("<html>Fail! R must be an Intager and more than 0.</html>");
            }
            int x = ((Integer)this.xComboBox.getSelectedItem()).intValue();
            int y = Integer.parseInt((String)this.ySpinner.getValue());
            infoXLabel.setText("x: " + String.valueOf(x));
            infoYLabel.setText("y: " + String.valueOf(y));
            this.setMark(new Mark(x, y));
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

    public void addCanvas(FigureDrawer canvas){
        this.panel.add(canvas, BorderLayout.CENTER);
        canvas.setLabels(infoXLabel, infoYLabel);
    }
    /*@Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        System.out.println(e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Welcome to Java Programming!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }*/
}
