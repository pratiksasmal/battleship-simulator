package battleship;

import java.util.*;

class WrongLengthException extends Exception {
}

class WrongLocationException extends Exception {
}

class TooCloseException extends Exception {
}

class Shift {
    final int x;
    final int y;

    Shift(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Ship {
    final String name;
    final int size;

    Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }
}


class Coordinate {
    final int x;
    final int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Position {
    final Coordinate start;
    final Coordinate stop;

    Position(String start, String stop) throws WrongLocationException {
        Coordinate firstCoordinate = parsePosition(start);
        Coordinate secondCoordinate = parsePosition(stop);

        if (firstCoordinate.x < secondCoordinate.x || firstCoordinate.y < secondCoordinate.y) {
            this.start = firstCoordinate;
            this.stop = secondCoordinate;
        } else {
            this.start = secondCoordinate;
            this.stop = firstCoordinate;
        }
    }

    private Coordinate parsePosition(String position) throws WrongLocationException {
        int x = position.endsWith("10") ? 9 : position.charAt(1) - 49;
        int y = position.charAt(0) - 65;

        if (x > 9 || y < 0 || y > 9) {
            throw new WrongLocationException();
        }

        return new Coordinate(x, y);
    }
}
class Game {
    final String[][] board = new String[10][];
    final String[] ROW_KEYS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    final Ship[] SHIPS = {
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    };

    Game() {
        //to make an empty array
        for (int i = 0; i < 10; i++) {
            String[] row = new String[10];
            Arrays.fill(row, "~");
            board[i] = row;
        }
    }
    void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i+1);
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print(ROW_KEYS[i]);
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    Position readPosition() throws WrongLocationException {
        Scanner scanner = new Scanner(System.in);
        String start = scanner.next();
        String stop = scanner.next();
        return new Position(start, stop);
    }

    private boolean isValidSize(Position position, int size) {
        if (position.start.x == position.stop.x) {
            return size == position.stop.y - position.start.y + 1;
        } else if (position.start.y == position.stop.y) {
            return size == position.stop.x - position.start.x + 1;
        } else {
            return false;
        }
    }
}
public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.printBoard();
        //game.placeShips();
    }
}
