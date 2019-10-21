package ru.stqa.pft.sandbox;
import static ru.stqa.pft.sandbox.Point.distance;

public class myFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Alexei");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(10,15);
        Point p2 = new Point(13,19);
        //System.out.println("Длина равна " + distance());
        System.out.println("Длина равна " + distance(p1,p2));

        Point p = new Point(10,13,15,19);
        System.out.println("Длина равна " + p.distance());


    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    /*public static double distance(Point p1, Point p2){
        double hyp2 = (p2.x - p1.x) * (p2.x - p1.x) + Math.pow(p2.y - p1.y, 2);
        double hyp = Math.sqrt(hyp2);
        return hyp;
    }*/
}