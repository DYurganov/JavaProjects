package Controller;

import View.PlayerDialog;

public interface Controller {
    boolean parseCommand(String command, PlayerDialog dialog);
}
