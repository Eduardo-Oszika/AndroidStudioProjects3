package com.oszika.exemplosharepreferences;

import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Corutil {
    public static void setCor(View layout, String cor, Context context){
        int corFundo;
        switch (cor){
            case "Azul":
                corFundo = ContextCompat.getColor(context, android.R.color.holo_green_light);
                break;
            case "Vermelho":
                corFundo = ContextCompat.getColor(context, android.R.color.holo_red_light);
                break;
            case "Verde":
                corFundo = ContextCompat.getColor(context, android.R.color.holo_blue_light);
                break;
            default:
                corFundo = ContextCompat.getColor(context, android.R.color.white);
                break;
        }
        layout.setBackgroundColor(corFundo);
    }
}
