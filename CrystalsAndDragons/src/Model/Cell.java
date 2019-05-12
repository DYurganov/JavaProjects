package Model;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private boolean northDoor;
    private boolean southDoor;
    private boolean eastDoor;
    private boolean westDoor;
    private ArrayList<Item> items;

    Cell() {
        northDoor = false;
        southDoor = false;
        eastDoor = false;
        westDoor = false;
        Random itemsNumber = new Random();
        int countItem = itemsNumber.nextInt(5);
        items = new ArrayList<>();
        for (int i = 0; i < countItem; i++) {
            items.add(Item.giveRandomItem());
        }
    }

    public boolean isContainChest() {
        return items.contains(Item.CHEST);
    }

    public boolean isContainKey() {
        return items.contains(Item.KEY);
    }

    public void addChest() {
        items.add(Item.CHEST);
    }

    public void addKey() {
        items.add(Item.KEY);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setNorthDoor(boolean northDoor) {
        this.northDoor = northDoor;
    }

    public void setSouthDoor(boolean southDoor) {
        this.southDoor = southDoor;
    }

    public void setEastDoor(boolean eastDoor) {
        this.eastDoor = eastDoor;
    }

    public void setWestDoor(boolean westDoor) {
        this.westDoor = westDoor;
    }

    public boolean isNorthDoor() {
        return northDoor;
    }

    public boolean isSouthDoor() {
        return southDoor;
    }

    public boolean isEastDoor() {
        return eastDoor;
    }

    public boolean isWestDoor() {
        return westDoor;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
