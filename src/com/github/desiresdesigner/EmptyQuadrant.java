package com.github.desiresdesigner;

import java.awt.*;

/**
 * @author desiresdesigner
 * @since 28.10.13
 */

public class EmptyQuadrant extends AbstractQuadrant {

  public EmptyQuadrant(int number) {
    super(number);
  }

  @Override
  public void draw(Graphics g, Mark center) {}
}
