package Model;

import java.util.ArrayList;

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
        items = new ArrayList<>();
        CellFiller cellFiller = new CellFiller(this);
        cellFiller.fillCell();
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
