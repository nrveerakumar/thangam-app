package com.thangam.onlineshopping.myclass;
import android.content.Context;

import com.thangam.onlineshopping.HomeDatabase;
import com.thangam.onlineshopping.ItemClass;
import com.thangam.onlineshopping.R;

import java.util.ArrayList;

public class LoadData {

    public LoadData(Context context){
        ArrayList<ItemClass> homeArrayList = new ArrayList<>();

        int itemCartColor = R.color.cart_image_red;
        homeArrayList.add(new ItemClass("bricks","Bricks are one of the oldest and most fundamental building materials used in construction",
                900,R.drawable.bricks, itemCartColor,0));

        homeArrayList.add(new ItemClass("Cement","Cement is a crucial building material that hardens and sticks to other materials, helping them stick together. It's not typically used alone; instead,/n it's mixed with sand and gravel.",
                249,R.drawable.cement, itemCartColor,0));

        homeArrayList.add(new ItemClass("Jalli1/2", "Stone is a natural building material formed over centuries through geological processes.", 1000,
                R.drawable.jalli, itemCartColor,0));

        homeArrayList.add(new ItemClass("Jalli 1","Stone is a natural building material formed over centuries through geological processes.",1200,R.drawable.jalli_1, itemCartColor,0));

        homeArrayList.add(new ItemClass("Msand","M-sand, or manufactured sand, is an artificial sand made from crushing hard stones into small, angular particles and then washing and grading them",1500,R.drawable.msand, itemCartColor,0));

        homeArrayList.add(new ItemClass("Psand","P-sand stands for Plastering Manufactured Sand, and it's a type of artificial sand used in construction as a substitute for river sand.",1300,R.drawable.psand, itemCartColor,0));

        homeArrayList.add(new ItemClass("steel","Steel is a strong metal made from iron and carbon, with other materials mixed in to make it even stronger and resistant to damage than iron. ",530,R.drawable.steel, itemCartColor,0));

        homeArrayList.add(new ItemClass("hollowbrick","Steel is a strong metal made from iron and carbon, with other materials mixed in to make it even stronger and resistant to damage than iron. ",35,R.drawable.hollowbricks, itemCartColor,0));
        homeArrayList.add(new ItemClass("steel","Steel is a strong metal made from iron and carbon, with other materials mixed in to make it even stronger and resistant to damage than iron. ",25,R.drawable.steel, itemCartColor,0));

        HomeDatabase k = new HomeDatabase( context);
        for (int i = 0; i < homeArrayList.size(); i++) {
            ItemClass itemClass = homeArrayList.get(i);
            k.addData(itemClass.itemName,itemClass.itemDisc, itemClass.prise,itemClass.image,itemClass.itemCartColor,itemClass.isCart);
        }


    }
}
