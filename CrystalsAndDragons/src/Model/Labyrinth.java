package Model;

import java.util.*;

public class Labyrinth {
    private int verticalSize;
    private int horizontalSize;
    private int enterCave;
    private Cell[][] labyrinth;

    public Labyrinth(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        labyrinth = new Cell[verticalSize][horizontalSize];
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                labyrinth[i][j] = new Cell();
            }
        }
        buildRandomLabyrinth();
        Random inputPosition = new Random();
        enterCave = inputPosition.nextInt(verticalSize * horizontalSize);
        int chestPosition = horizontalSize * inputPosition.nextInt(verticalSize) + inputPosition.nextInt(horizontalSize);
        int keyStartPosition = horizontalSize * inputPosition.nextInt(verticalSize) + inputPosition.nextInt(horizontalSize);
        labyrinth[chestPosition / horizontalSize][chestPosition % horizontalSize].addChest();
        labyrinth[keyStartPosition / horizontalSize][keyStartPosition % horizontalSize].addKey();
    }

    private void buildRandomLabyrinth() {
        ArrayList<Boolean> visitedCell = new ArrayList<>(horizontalSize * verticalSize);
        for (int i = 0; i < horizontalSize * verticalSize; i++) {
            visitedCell.add(false);
        }
        Stack<Integer> stackNodes = new Stack<>();
        Random random = new Random();
        int currentNode = random.nextInt(horizontalSize * verticalSize);
        visitedCell.set(currentNode, true);
        while (visitedCell.contains(false)) {
            if (currentNode % horizontalSize < horizontalSize - 1 && !visitedCell.get(currentNode + 1)) {
                stackNodes.push(currentNode);
                currentNode = choosePath(currentNode, visitedCell);
                continue;
            }
            if (currentNode % horizontalSize > 0 && !visitedCell.get(currentNode - 1)) {
                stackNodes.push(currentNode);
                currentNode = choosePath(currentNode, visitedCell);
                continue;
            }
            if (currentNode - horizontalSize >= 0 && !visitedCell.get(currentNode - horizontalSize)) {
                stackNodes.push(currentNode);
                currentNode = choosePath(currentNode, visitedCell);
                continue;
            }
            if (currentNode + horizontalSize < visitedCell.size() && !visitedCell.get(currentNode + horizontalSize)) {
                stackNodes.push(currentNode);
                currentNode = choosePath(currentNode, visitedCell);
                continue;
            }
            if (!stackNodes.empty()) {
                currentNode = stackNodes.pop();
            }
        }
    }

    private int choosePath(int currentNode, ArrayList<Boolean> visitedCell) {
        while (true) {
            Random random = new Random();
            int nextDoor = random.nextInt(4);
            switch (nextDoor) {
                case 0:
                    if (currentNode % horizontalSize != 0 && !visitedCell.get(currentNode - 1)) {
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setWestDoor(true);
                        currentNode--;
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setEastDoor(true);
                        visitedCell.set(currentNode, true);
                        return currentNode;
                    }
                    break;
                case 1:
                    if (currentNode % horizontalSize < horizontalSize - 1 && !visitedCell.get(currentNode + 1)) {
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setEastDoor(true);
                        currentNode++;
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setWestDoor(true);
                        visitedCell.set(currentNode, true);
                        return currentNode;
                    }
                    break;
                case 2:
                    if (currentNode / horizontalSize < verticalSize - 1 && !visitedCell.get(currentNode + horizontalSize)) {
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setSouthDoor(true);
                        currentNode += horizontalSize;
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setNorthDoor(true);
                        visitedCell.set(currentNode, true);
                        return currentNode;
                    }
                    break;
                case 3:
                    if (currentNode / horizontalSize != 0 && !visitedCell.get(currentNode - horizontalSize)) {
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setNorthDoor(true);
                        currentNode -= horizontalSize;
                        labyrinth[currentNode / horizontalSize][currentNode % horizontalSize].setSouthDoor(true);
                        visitedCell.set(currentNode, true);
                        return currentNode;
                    }
                    break;
            }
        }
    }

    public int minTurn() {
        int turns = 0;
        int turnToFindChest = -1;
        ArrayList<Boolean> visitedCell = new ArrayList<>(horizontalSize * verticalSize);
        for (int i = 0; i < horizontalSize * verticalSize; i++) {
            visitedCell.add(false);
        }
        Stack<Integer> stackNodes = new Stack<>();
        int curPos = enterCave;
        visitedCell.set(curPos, true);
        boolean keyFound = false;
        while (!keyFound && turnToFindChest < 0) {
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isContainKey()) {
                keyFound = true;
            }
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isContainChest()) {
                turnToFindChest = turns;
            }
            turns++;
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isWestDoor() && !visitedCell.get(curPos - 1)) {
                stackNodes.push(curPos);
                curPos--;
                visitedCell.set(curPos, true);
                continue;
            }
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isEastDoor() && !visitedCell.get(curPos + 1)) {
                stackNodes.push(curPos);
                curPos++;
                visitedCell.set(curPos, true);
                continue;
            }
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isNorthDoor() && !visitedCell.get(curPos - horizontalSize)) {
                stackNodes.push(curPos);
                curPos -= horizontalSize;
                visitedCell.set(curPos, true);
                continue;
            }
            if (labyrinth[curPos / horizontalSize][curPos % horizontalSize].isSouthDoor() && !visitedCell.get(curPos + horizontalSize)) {
                stackNodes.push(curPos);
                curPos += horizontalSize;
                visitedCell.set(curPos, true);
                continue;
            }
            if (!stackNodes.empty()) {
                curPos = stackNodes.pop();
            } else {
                break;
            }
        }
        return turns + turns - turnToFindChest;
    }

    public int numberOfOpenDoors(int x, int y) {
        int N = 0;
        if (labyrinth[y][x].isNorthDoor()) {
            N++;
        }
        if (labyrinth[y][x].isSouthDoor()) {
            N++;
        }
        if (labyrinth[y][x].isWestDoor()) {
            N++;
        }
        if (labyrinth[y][x].isEastDoor()) {
            N++;
        }
        return N;
    }

    public Map<String, Boolean> openedDoor(int x, int y) {
        Map<String, Boolean> direction = new HashMap<>();
        direction.put("N", labyrinth[y][x].isNorthDoor());
        direction.put("S", labyrinth[y][x].isSouthDoor());
        direction.put("W", labyrinth[y][x].isWestDoor());
        direction.put("E", labyrinth[y][x].isEastDoor());
        return direction;
    }

    public ArrayList<Item> getCellItemList(int x, int y) {
        return labyrinth[y][x].getItems();
    }

    public void dropItemInCell(int x, int y, Item item) {
        labyrinth[y][x].addItem(item);
    }

    public void deleteItemFromCell(int x, int y, Item item) {
        labyrinth[y][x].removeItem(item);
    }

    public int getEnterCave() {
        return enterCave;
    }
}
