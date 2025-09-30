package com.oszika.listviewsimples;

import java.util.ArrayList;

public class Repository {
    public static ArrayList<String> getLinguagens(){
        ArrayList<String> linguagens = new ArrayList<>();
        linguagens.add("Java");
        linguagens.add("Kotlin");
        linguagens.add("JavaScript");
        linguagens.add("Python");
        linguagens.add("C#");
        linguagens.add("C++");
        return linguagens;
    }
}
