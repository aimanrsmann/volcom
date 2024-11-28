package com.example.Volcom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Record {

    @Id
    private int id;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private double price; // Changed to double

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; } // Getter for double price
    public void setPrice(double price) { this.price = price; } // Setter for double price
}
