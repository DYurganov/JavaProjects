package Model;

import java.util.ArrayList;

public class Player {
    private ArrayList<Item> inventory;
    private int currentXPos;
    private int currentYPos;
    private int life;

    public Player(int x, int y) {
        inventory = new ArrayList<>();
        currentXPos = x;
        currentYPos = y;
        life = 25;
    }

    public void addLife(int life) {
        this.life += life;
    }

    public void getItem(Item newItem) {
        if (newItem == Item.CHEST) {
            return;
        }
        inventory.add(newItem);
    }

    public boolean haveTheKey() {
        return inventory.contains(Item.KEY);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getX() {
        return currentXPos;
    }

    public int getY() {
        return currentYPos;
    }

    public int getLife() {
        return life;
    }

    public void dropItem(Item droppingItem) {
        inventory.remove(droppingItem);
    }

    public boolean containItem(Item item) {
        return inventory.contains(item);
    }

    public void move(char direction) {
        life--;
        switch (direction) {
            case 'S':
                currentYPos++;
                break;
            case 'N':
                currentYPos--;
                break;
            case 'E':
                currentXPos++;
                break;
            case 'W':
                currentXPos--;
                break;
            default:
                break;
        }

    }
}
