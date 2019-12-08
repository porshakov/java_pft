package ru.stqa.pft.sandbox;

public class Equality {
  public static void main(String[] args){
    String s1 = "firefox 2.0";
    String s2 = "firefox " + Math.sqrt(4.0);

    // Сравнение ссылок
    System.out.println(s1 == s2);
    // Сравнение объектов
    System.out.println(s1.equals(s2));
  }
}
