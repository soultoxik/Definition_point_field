package com.github.desiresdesigner;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: desiresdesigner
 * Date: 11/11/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class PointAnimationThread extends Thread implements Drawable {
    //@Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        System.out.println("Привет из побочного потока!");
    }

    @Override
    public void draw(Graphics g, Mark center){

    }
}
