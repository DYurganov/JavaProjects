package View;

import Model.Item;

import java.util.ArrayList;
import java.util.Map;

public interface View {
    void displayWelcomeWindow();

    int readNumber();

    void wrongInput();

    void errorItem();

    void wrongDoor();

    void showHelp();

    void defeat();

    void victory();

    void readPlayerCommand();

    void impossibleAction();

    void makeTurn(int x, int y, int N, Map<String, Boolean> directions, ArrayList<Item> itemList);

    void showInventory(ArrayList<Item> inventory);
}
