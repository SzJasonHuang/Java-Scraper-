package com.data;

public class Product {
    
    private String url; 
    private String image; 
    private String name; 
    private String price;

    public void setUrl(String input){
        this.url = input; 
    }

    public void setImage(String image){
        this.image = image;
    }
    public void setName(String name){
        this.name = name; 
    }
    public void setPrice(String price){
        this.price = price; 
    }


    @Override 
    public String toString() {
        return "{ \"url\":\"" + url + "\", " 
				+ " \"image\": \"" + image + "\", " 
				+ "\"name\":\"" + name + "\", " 
				+ "\"price\": \"" + price + "\" }";

    }
}
