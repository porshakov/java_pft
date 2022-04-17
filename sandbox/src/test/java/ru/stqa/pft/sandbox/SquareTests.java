package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test
  public void testAreaL5(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 20.0);
  }

  @Test
  public void testAreaL99(){
    Square s = new Square(99);
    Assert.assertEquals(s.area(), 9801.0);
  }
}
