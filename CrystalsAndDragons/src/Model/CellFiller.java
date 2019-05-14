package Model;

import java.util.Random;

public class CellFiller {
    private Cell cell;

    CellFiller(Cell cell) {
        this.cell = cell;
    }

    public void fillCell() {
        Random itemsNumber = new Random();
        int countItem = itemsNumber.nextInt(5);
        for (int i = 0; i < countItem; i++) {
            cell.addItem(Item.giveRandomItem());
        }
    }
}
