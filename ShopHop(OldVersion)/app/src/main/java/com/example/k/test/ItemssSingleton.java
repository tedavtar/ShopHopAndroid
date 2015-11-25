package com.example.k.test;

import java.util.ArrayList;

/**
 * Created by k on 12/1/14.
 */
public class ItemssSingleton {
    private static ItemssSingleton itemListSingleton;
    private ArrayList<Item> knownItems = new ArrayList<Item>(); //PROLLY NOT but maybe do some database stuff to let use populate this???

    private ItemssSingleton(){
        //http://stackoverflow.com/questions/6180802/android-imageview-programmatically
        //HERE IS WHERE WE CREATE ALL OUR ITEMS THAT WE'LL MATCH USER INPUT AGAINST
        Item first = new Item("Apple", "    It's your classic red apple.",R.drawable.apple, "Aisle #1");
        Item second = new Item("Steak", "    Nice, juicy cut of meat.",R.drawable.steak, "Aisle #3");
        Item third = new Item("Eggs", "    May or may not be vegetarian.",R.drawable.egg, "Aisle #2");
        Item fourth = new Item("Milk", "    Good for cereal!",R.drawable.milk, "Aisle #6");
        Item fifth = new Item("Bananas", "    Brain food!",R.drawable.banana, "Aisle #1");
        //Item sixth = new Item("Cheese", "Some like them sour",R.drawable.);
        Item seventh = new Item("Bagels", "    The cost-efficient, delicious breakfast.",R.drawable.bagel, "Aisle #7");

        Item ninth = new Item("Tofu", "    Meat for vegetarians!",R.drawable.tofu, "Aisle #5");
        Item tenth = new Item("Salmon", "    The tastiest fish in the sea.",R.drawable.salmon, "Aisle #10");


        knownItems.add(first);
        knownItems.add(second);


        knownItems.add(third);
        knownItems.add(fourth);
        knownItems.add(fifth);
        //knownItems.add(sixth);
        knownItems.add(seventh);
        //knownItems.add(eighth);
        knownItems.add(ninth);
        knownItems.add(tenth);


    }

    public static ItemssSingleton get(){
        if (itemListSingleton == null) {
            itemListSingleton = new ItemssSingleton();
        }
        return itemListSingleton;
    }
    //So basically user wants to add item to list.  Need to check if requested to add a valid item that we recognize
    public Item isValidInput(String itemName){
        Item rtn = null;
        for (Item i : knownItems){
            if (i.getName().equalsIgnoreCase(itemName)){
                rtn = i;
            }
        }
        return rtn;
    }

    //can also use this if we allow users to create custom items and add to item list (ex. take picture of an item give it a name description, maybe aisle number, price, idk)
    public ArrayList<Item> getKnownItems(){
        return knownItems;
    }

}
