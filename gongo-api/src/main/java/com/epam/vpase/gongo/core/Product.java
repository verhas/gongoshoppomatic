package com.epam.vpase.gongo.core;

public class Product implements HasId {
    public String id;
    public String asins;
    public String brand;
    public String categories;
    public String colors;
    public String dateAdded;
    public String dateUpdated;
    public String dimension;
    public String ean;
    public String imageURLs;
    public String keys;
    public String manufacturer;
    public String manufacturerNumber;
    public String name;
    public String primaryCategories;
    public String reviewsDate;
    public String reviewsDateSeen;
    public String reviewsDoRecommend;
    public String reviewsNumHelpful;
    public String reviewsRating;
    public String reviewsSourceURLs;
    public String reviewsText;
    public String reviewsTitle;
    public String reviewsUsername;
    public String sourceURLs;
    public String upc;
    public String weight;

    @Override
    public String getId() {
        return id;
    }

}
