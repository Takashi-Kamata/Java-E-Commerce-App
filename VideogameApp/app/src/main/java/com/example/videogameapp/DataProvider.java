//Class that simulates a database by creating the data necessary to run the application
package com.example.videogameapp;

import java.util.ArrayList;
import java.util.Random;

public class DataProvider {
    //String array containing the 30 videogame titles to simulate a database
    //the array is arranged by the game gnere, first 10 is RPG, second 10 is Action & third 10 is FPS

    static String[] titles = {"Skyrim", "Mass Effect 3", "Dark Souls", "Witcher 3", "Dark Souls II",
            "Cyberpunk 2077", "Fallout 4", "The Outer Worlds", "Assassin's Creed: Odyssey","Stardew Valley",
            "Grand Theft Auto 5", "Uncharted 4", "God of War", "Tomb Raider", "Red Dead Redemption 2",
            "Spider-Man", "Batman Arkham City", "Resident Evil Village", "Horizon Zero Dawn", "Street Fighter 5",
            "Overwatch", "Apex Legends", "Destiny 2", "Half-Life 2", "Bioshock",
            "Borderlands 3", "DOOM Eternal", "Far Cry 5", "Call Of Duty: Black Ops 2", "Call of Duty: Modern Warfare" };
    //function to mimic the creation of a Videogame class Array List, mimicking information gathering from a database
    public static ArrayList<Videogame> generateData(){
        ArrayList<Videogame> videogames = new ArrayList<Videogame>();
        //generates a Random class to be used with the integered information of the game
        //this way, those values when generated will always be random
        Random rd = new Random();
        for(int i = 0; i< titles.length; i++){//loop through entire titles array
            String genre = "";
            //generate the genre based off the index position of the game
            if (i < 10){
                genre = "RPG";
            }else if (i < 20){
                genre = "Action";
            }else {
                genre = "FPS";
            }
            float price = rd.nextFloat()*120;
            float rating = rd.nextFloat()*5;
            boolean isPreOwned = rd.nextBoolean();
            String gameBlurb =  "a";
            Integer viewCount = 0;
            Videogame aVideogame = new Videogame(titles[i],genre,price,rating,isPreOwned,gameBlurb,viewCount);
            videogames.add(aVideogame);
        }
        return videogames;
    }
}
