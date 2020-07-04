package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    /*String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";
    */
    String[] langs = {"Java", "C#", "Python", "PHP"};

    /*for(int i = 0; i < langs.length; i++){
      System.out.println("Я хочу выучить " + langs[i]);
    }

    for(String l: langs){
      System.out.println("Я хочу выучить " + l);
    }
    */

    // Размер массива задается сразу и не изменяется
    // А размер коллекции list (список) увеличивается и уменьшается по мере добавления и уменьшения соответственно
    /*List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    */

    // Можно задать инициализацию как у массива через Arrays.asList
    //List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    /*for(String l: languages){
      System.out.println("Я хочу выучить " + l);
    }
    */

    // Обращение к элементам списка как в объекте (i)
    /*for(int i = 0; i < languages.size(); i++) {
      System.out.println ("Я хочу выучить " + languages.get(i));
    }
    */

    // Если не указан тип элементов, то в цикле будет Object
    List languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for(Object l: languages){
      System.out.println("Я хочу выучить " + l);
    }
  }
}
