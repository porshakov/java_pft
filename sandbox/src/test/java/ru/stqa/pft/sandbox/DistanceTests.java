package ru.stqa.pft.sandbox;
import static ru.stqa.pft.sandbox.Point.distance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistance1() {
    Point p = new Point(10,13,15,19);
    Assert.assertEquals(p.distance(), 5);
  }

  /*@Test
  public void testDistance2() {
    //Point p = new Point(8,1,15,19);
    Point p1 = new Point(7,10);
    Point p2 = new Point(18,22);
    Assert.assertEquals(distance(p1, p2), 5);
  }*/
}
