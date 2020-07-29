package com.example.realestatemw;

import java.io.Serializable;

public class Agent implements Serializable  {

    private String city;
    private String town;
    private String image;
    private String images;
    private String id;
    private String price;
    private String beds;
    private String type;
    private String bath;


    public Agent() {
    }

    public Agent(String images, String city, String town,String bath, String image,String price, String beds, String type) {
        this.setCity(city);
        this.setTown(town);
        this.setImage(image);
        this.setPrice(price);
        this.setBeds(beds);
        this.setType(type);
        this.setBath(bath);
        //this.setImages(images);

    }

//    public String getImages (){return images;}
//    public  void  setImages (String images){this.images = images;}
    public String getBath (){return bath;}
    public void setBath (String bath){this.bath = bath;}

    public String getType(){return type;}
    public  void  setType(String type){this.type = type;}

    public String getBeds(){return beds;}
    public void setBeds(String beds){this.beds = beds;}

    public String getPrice(){return price;}
    public void  setPrice(String price){this.price = price; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", image='" + image + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}