package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public double x1;
  public double y1;
  public double x2;
  public double y2;

  public Point(double x, double y){
      this.x = x;
      this.y = y;
  }

  public Point(double x1, double x2, double y1, double y2){
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public static double distance(Point p1, Point p2){
    double hyp2 = (p2.x - p1.x) * (p2.x - p1.x) + Math.pow(p2.y - p1.y, 2);
    double hyp = Math.sqrt(hyp2);
    return hyp;
  }

  public double distance(){
    double hyp2 = (this.x2 - this.x1) * (this.x2 - this.x1) + Math.pow(this.y2 - this.y1, 2);
    double hyp = Math.sqrt(hyp2);
    return hyp;
  }
}
