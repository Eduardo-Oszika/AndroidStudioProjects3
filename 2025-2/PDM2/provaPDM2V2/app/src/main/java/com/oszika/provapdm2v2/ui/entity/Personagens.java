package com.oszika.provapdm2v2.ui.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Personagens {

    @SerializedName("data")
    @Expose
    private List<Personagem> data;

    /**
     * No args constructor for use in serialization
     */
    public Personagens() {
    }

    public Personagens(List<Personagem> data) {
        super();
        this.data = data;
    }

    public List<Personagem> getData() {
        return data;
    }

    public void setData(List<Personagem> data) {
        this.data = data;
    }

}
