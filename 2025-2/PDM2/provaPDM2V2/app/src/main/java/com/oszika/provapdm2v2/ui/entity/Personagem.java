package com.oszika.provapdm2v2.ui.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Personagem {

    @SerializedName("_id")
    @Expose
    private Integer id;

    @SerializedName("films")
    @Expose
    private List<String> films;
    @SerializedName("shortFilms")
    @Expose
    private List<String> shortFilms;
    @SerializedName("tvShows")
    @Expose
    private List<String> tvShows;
    @SerializedName("videoGames")
    @Expose
    private List<String> videoGames;
    @SerializedName("parkAttractions")
    @Expose
    private List<String> parkAttractions;
    @SerializedName("allies")
    @Expose
    private List<Object> allies;
    @SerializedName("enemies")
    @Expose
    private List<Object> enemies;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public Personagem() {
    }
    public Personagem(Integer id, List<String> films, List<String> shortFilms, List<String> tvShows, List<String> videoGames, List<String> parkAttractions, List<Object> allies, List<Object> enemies, String name, String imageUrl, String url) {
        super();
        this.id = id;
        this.films = films;
        this.shortFilms = shortFilms;
        this.tvShows = tvShows;
        this.videoGames = videoGames;
        this.parkAttractions = parkAttractions;
        this.allies = allies;
        this.enemies = enemies;
        this.name = name;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getShortFilms() {
        return shortFilms;
    }

    public void setShortFilms(List<String> shortFilms) {
        this.shortFilms = shortFilms;
    }

    public List<String> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<String> tvShows) {
        this.tvShows = tvShows;
    }

    public List<String> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<String> videoGames) {
        this.videoGames = videoGames;
    }

    public List<String> getParkAttractions() {
        return parkAttractions;
    }

    public void setParkAttractions(List<String> parkAttractions) {
        this.parkAttractions = parkAttractions;
    }

    public List<Object> getAllies() {
        return allies;
    }

    public void setAllies(List<Object> allies) {
        this.allies = allies;
    }

    public List<Object> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Object> enemies) {
        this.enemies = enemies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
