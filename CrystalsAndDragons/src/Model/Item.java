package Model;

import java.util.Random;

public enum Item {
    CHEST,
    KEY,
    STONE,
    BONE,
    CHANE;

    public static Item giveRandomItem() {
        Random randomItem = new Random();
        return (Item.values()[randomItem.nextInt(Item.values().length - 2) + 2]);  //return random item except chest and key
    }
}
