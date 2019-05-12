package Controller;

import Model.Item;
import Model.Labyrinth;
import Model.Player;
import View.PlayerDialog;
import java.util.ArrayList;
import java.util.Map;

public class Game {
    private Labyrinth labyrinth;
    private Player player;
    private boolean endGame;

    public Game() {
        endGame = false;
        PlayerDialog dialog = new PlayerDialog();
        dialog.displayWelcomeWindow();
        int verticalSize = dialog.readNumber();                         // read maze size
        int horizontalSize = dialog.readNumber();
        labyrinth = new Labyrinth(verticalSize, horizontalSize);        //maze generate
        player = new Player(labyrinth.getEnterCave() % horizontalSize, labyrinth.getEnterCave() / horizontalSize);
        if (labyrinth.minTurn() > player.getLife()) {                   //add more life for big maze
            player.addLife(labyrinth.minTurn());
        }
        while (!endGame) {                                              //game process
            Map<String, Boolean> directions;
            directions = labyrinth.openedDoor(player.getX(), player.getY());
            ArrayList<Item> cellItem = labyrinth.getCellItemList(player.getX(), player.getY());
            dialog.makeTurn(player.getX(), player.getY(), labyrinth.numberOfOpenDoors(player.getX(), player.getY()), directions, cellItem);
            String answer = dialog.readPlayerCommand();
            if (!parseCommand(answer, dialog)) {
                dialog.wrongInput();
            }
            if (player.getLife() == 0) {
                endGame = true;
                dialog.defeat();
            }
        }
    }

    private boolean parseCommand(String command, PlayerDialog dialog) {
        String[] spittedCommand = command.split(" ");
        switch (spittedCommand[0]) {
            case "-N":
                inputNorth(dialog);
                break;
            case "-S":
                inputSouth(dialog);
                break;
            case "-E":
                inputEast(dialog);
                break;
            case "-W":
                inputWest(dialog);
                break;
            case "-get":
                inputGet(dialog, spittedCommand[1]);
                break;
            case "-drop":
                inputDrop(dialog, spittedCommand[1]);
                break;
            case "-open":
                tryOpenChest(dialog);
                break;
            case "-h":
                dialog.showHelp();
                break;
            case "-inventory":
                dialog.showInventory(player.getInventory());
                break;
            default:
                return false;

        }
        return true;
    }

    private void tryOpenChest(PlayerDialog dialog) {
        if (player.haveTheKey() && labyrinth.getCellItemList(player.getX(), player.getY()).contains(Item.CHEST)) {
            endGame = true;
            dialog.victory();
        } else {
            dialog.impossibleAction();
        }
    }

    private void inputDrop(PlayerDialog dialog, String item) {
        Item droppedItem;
        if (parseItem(item)) {
            droppedItem = Item.valueOf(item);
            if (player.containItem(droppedItem)) {
                player.dropItem(droppedItem);
            } else {
                dialog.errorItem();
            }
            labyrinth.dropItemInCell(player.getX(), player.getY(), droppedItem);
        } else {
            dialog.errorItem();
        }
    }

    private void inputGet(PlayerDialog dialog, String item) {
        Item gettingItem;
        if (parseItem(item)) {
            gettingItem = Item.valueOf(item);
            if (labyrinth.getCellItemList(player.getX(), player.getY()).contains(gettingItem)) {
                player.getItem(gettingItem);
                labyrinth.deleteItemFromCell(player.getX(), player.getY(), gettingItem);
            } else {
                dialog.errorItem();
            }
        } else {
            dialog.errorItem();
        }
    }

    private void inputWest(PlayerDialog dialog) {
        if (labyrinth.openedDoor(player.getX(), player.getY()).get("W")) {
            player.move('W');
        } else {
            dialog.wrongDoor();
        }
    }

    private void inputEast(PlayerDialog dialog) {
        if (labyrinth.openedDoor(player.getX(), player.getY()).get("E")) {
            player.move('E');
        } else {
            dialog.wrongDoor();
        }
    }

    private void inputSouth(PlayerDialog dialog) {
        if (labyrinth.openedDoor(player.getX(), player.getY()).get("S")) {
            player.move('S');
        } else {
            dialog.wrongDoor();
        }
    }

    private void inputNorth(PlayerDialog dialog) {
        if (labyrinth.openedDoor(player.getX(), player.getY()).get("N")) {
            player.move('N');
        } else {
            dialog.wrongDoor();
        }
    }

    private boolean parseItem(String item) {
        try {
            Item.valueOf(item);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
