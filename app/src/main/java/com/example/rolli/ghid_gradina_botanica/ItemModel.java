package com.example.rolli.ghid_gradina_botanica;



public class ItemModel {

    private String itemName;
    private String itemImg;
    private String itemText;
    private String itemCategory;

    public ItemModel() {

    }

    public ItemModel(String itemName, String itemImg, String itemText, String itemCategory) {

        this.itemName = itemName;
        this.itemImg = itemImg;
        this.itemText = itemText;
        this.itemCategory = itemCategory;
    }

    public String getItemImg() {
        return itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemText() {
        return itemText;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemImg(String itemImg) {this.itemImg = itemImg;}

    public void setItemCategory(String itemCategory) {this.itemCategory = itemCategory;}

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
