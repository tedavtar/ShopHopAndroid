package com.example.k.test;

/**
 * Created by k on 12/1/14.
 */
public class Item {
    private String name;
    private String description;
    private int imagePath;
    private String isleNumber;

    public Item(String name, String description, int imagePath, String isleNumber){
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.isleNumber = isleNumber;
    }

    public String getName(){return name;}
    public String getDesc(){return description;}
    public int getImagePath(){return imagePath;}
    public String getIsleNumber(){return isleNumber;}



    //ADD FIELD FOR AN IMAGE. FOR NOW I'M JUST DOING NAME+DESC IN THE LISTVIEW but same idea holds

}
