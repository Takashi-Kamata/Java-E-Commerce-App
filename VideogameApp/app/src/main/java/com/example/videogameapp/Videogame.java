//Model Class for the data we need to use for each videogame item
package com.example.videogameapp;



import java.util.Comparator;

public class Videogame {
    //Initiate class Attributes
    private String name, genre, coverAddress,image3Address, image2Address, image1Address, gameBlurb;
    private float price, rating;
    private Integer viewCount;
    private boolean preOwned;

    //Initiate Constructor
    public Videogame(String name, String genre, float price, float rating,
                     boolean preOwned, String gameBlurb, Integer viewCount){
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.rating = rating;
        this.preOwned = preOwned;
        this.gameBlurb = gameBlurb;
        this.viewCount = viewCount;

        //The address of each image per game can be identified by the game name + prefix as to which image it is
        //The images on the drawable folder will follow this structure
        String addressTemp = name.toLowerCase().replaceAll("\\s+", "").replaceAll("[\\-':,]","");
        this.coverAddress = addressTemp + "cover";
        this.image1Address = addressTemp + "image1";
        this.image2Address = addressTemp + "image2";
        this.image3Address = addressTemp + "image3";

    }

    //Initiate Attribute getters
    public String getName(){return name;}
    public String getGenre(){return genre;}
    public float getPrice(){return price;}
    public float getRating(){return rating;}
    public boolean getPreOwned(){return preOwned;}
    public String getCoverAddress(){return coverAddress;}
    public String getImage1Address(){return image1Address;}
    public String getImage2Address(){return image2Address;}
    public String getImage3Address(){return image3Address;}
    public Integer getViewCount(){return viewCount;}

    public Integer addViewCount(){
        try {
            viewCount++;
        }
        catch(Exception e) {
            return 0;
        }
        return 1;
    }

    // Custom Comparator for Name A-Z/Z-A
    public static class CustomComparatorName implements Comparator<Videogame> {
        @Override
        public int compare(Videogame o1, Videogame o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    // Custom Comparator for Rating
    public static class CustomComparatorRating implements Comparator<Videogame> {
        @Override
        public int compare(Videogame o1, Videogame o2) {
            return Float.valueOf(o1.getRating()).compareTo(Float.valueOf(o2.getRating()));
        }
    }
    // Custom Comparator for Price
    public static class CustomComparatorPrice implements Comparator<Videogame> {
        @Override
        public int compare(Videogame o1, Videogame o2) {
            return Float.valueOf(o1.getPrice()).compareTo(Float.valueOf(o2.getPrice()));
        }
    }
    // Custom Comparator for View Count
    public static class CustomComparatorView implements Comparator<Videogame> {
        @Override
        public int compare(Videogame o1, Videogame o2) {
            return o1.getViewCount().compareTo(o2.getViewCount());
        }
    }
}
