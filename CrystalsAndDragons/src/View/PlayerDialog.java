package View;

import Controller.Controller;
import Model.Item;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class PlayerDialog implements View {
    private Scanner in;
    private Controller game;

    public PlayerDialog(Controller game) {
        this.game = game;
        in = new Scanner(System.in);
    }

    @Override
    public void displayWelcomeWindow() {
        System.out.println("Hello, please enter labyrinth size");
    }

    @Override
    public int readNumber() {
        while (!in.hasNextInt()) {
            System.out.println("One of the number is incorrect, please enter it again");
            in.next();
        }
        return in.nextInt();
    }

    @Override
    public void wrongInput() {
        System.out.println("You entered wrong input, try again or -h for help");
    }

    @Override
    public void errorItem() {
        System.out.println("Wrong item");
    }

    @Override
    public void wrongDoor() {
        System.out.println("This door is closed");
    }

    @Override
    public void showHelp() {
        System.out.println("Your objective is find key, chest and use key of chest\n" +
                "Use command -S -W -E -N to move through the maze through the open doors with -open\n" +
                "Use -drop -get to manage inventory\n" +
                "Every move spend your life point, when they run out - you die \n" +
                "Use -inventory to show your hero inventory\n" +
                "Good luck!");
    }

    @Override
    public void defeat() {
        System.out.println("Your strength dried up, you died in the dark dungeons of the dragon cave.");
    }

    @Override
    public void victory() {
        System.out.println("Congratulation! You found the treasure of the dragon cave");
    }

    @Override
    public void makeTurn(int x, int y, int N, Map<String, Boolean> directions, ArrayList<Item> itemList) {
        StringBuilder outputString = new StringBuilder("You are in the room ");
        outputString.append(x);
        outputString.append(',');
        outputString.append(y);
        outputString.append(". There are ");
        outputString.append(N);
        outputString.append(" doors:");
        if (directions.get("N")) {
            outputString.append(" ");
            outputString.append('N');
        }
        if (directions.get("S")) {
            outputString.append(" ");
            outputString.append('S');
        }
        if (directions.get("W")) {
            outputString.append(" ");
            outputString.append('W');
        }
        if (directions.get("E")) {
            outputString.append(" ");
            outputString.append('E');
        }
        outputString.append(". Items in the room:");
        for (Item anItemList : itemList) {
            outputString.append(" ");
            outputString.append(anItemList);
        }
        if (itemList.size() == 0) {
            outputString.append(" empty room");
        }
        System.out.println(outputString);
    }

    @Override
    public void showInventory(ArrayList<Item> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        StringBuilder outputString = new StringBuilder();
        for (Item item : inventory) {
            outputString.append(item.toString());
            outputString.append(' ');
        }
        System.out.println(outputString);
    }

    @Override
    public void readPlayerCommand() {
        while (!in.hasNext()) {
        }
        String input = in.nextLine();
        if (input.isEmpty()) {
            input = in.nextLine();
        }
        game.parseCommand(input, this);
    }

    @Override
    public void impossibleAction() {
        System.out.println("You can't do this now");
    }

}
