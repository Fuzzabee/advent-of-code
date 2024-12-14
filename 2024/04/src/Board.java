import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Character>> board;

    public Board(ArrayList<ArrayList<Character>> board) {
        this.board = board;
    }

    public int checkForXmasP1(Point point) {
        int numXmas = 0;
        int initX = point.getX();
        int initY = point.getY();

        // Checking N
        if (initY - 3 >= 0) {
            if (this.getChar(initX, initY - 1) == 'M' &&
                    this.getChar(initX, initY - 2) == 'A' &&
                    this.getChar(initX, initY - 3) == 'S') {
//                System.out.printf("Found N at %s\n", point);
                numXmas++;
            }
        }

        // Checking NE
        if (initY - 3 >= 0 && initX + 3 < this.board.getFirst().size()) {
            if (this.getChar(initX + 1, initY - 1) == 'M' &&
                    this.getChar(initX + 2, initY - 2) == 'A' &&
                    this.getChar(initX + 3, initY - 3) == 'S') {
//                System.out.printf("Found NE at %s\n", point);
                numXmas++;
            }
        }

        // Checking E
        if (initX + 3 < this.board.getFirst().size()) {
            if (this.getChar(initX + 1, initY) == 'M' &&
                    this.getChar(initX + 2, initY) == 'A' &&
                    this.getChar(initX + 3, initY) == 'S') {
//                System.out.printf("Found E at %s\n", point);
                numXmas++;
            }
        }

        // Checking SE
        if (initY + 3 < this.board.size() && initX + 3 < this.board.getFirst().size()) {
            if (this.getChar(initX + 1, initY + 1) == 'M' &&
                    this.getChar(initX + 2, initY + 2) == 'A' &&
                    this.getChar(initX + 3, initY + 3) == 'S') {
//                System.out.printf("Found SE at %s\n", point);
                numXmas++;
            }
        }

        // Checking S
        if (initY + 3 < this.board.size()) {
            if (this.getChar(initX, initY + 1) == 'M' &&
                    this.getChar(initX, initY + 2) == 'A' &&
                    this.getChar(initX, initY + 3) == 'S') {
//                System.out.printf("Found S at %s\n", point);
                numXmas++;
            }
        }

        // Checking SW
        if (initY + 3 < this.board.size() && initX - 3 >= 0) {
            if (this.getChar(initX - 1, initY + 1) == 'M' &&
                    this.getChar(initX - 2, initY + 2) == 'A' &&
                    this.getChar(initX - 3, initY + 3) == 'S') {
//                System.out.printf("Found SW at %s\n", point);
                numXmas++;
            }
        }

        // Checking W
        if (initX - 3 >= 0) {
            if (this.getChar(initX - 1, initY) == 'M' &&
                    this.getChar(initX - 2, initY) == 'A' &&
                    this.getChar(initX - 3, initY) == 'S') {
//                System.out.printf("Found W at %s\n", point);
                numXmas++;
            }
        }

        // Checking NW
        if (initY - 3 >= 0 && initX - 3 >= 0) {
            if (this.getChar(initX - 1, initY - 1) == 'M' &&
                    this.getChar(initX - 2, initY - 2) == 'A' &&
                    this.getChar(initX - 3, initY - 3) == 'S') {
//                System.out.printf("Found NW at %s\n", point);
                numXmas++;
            }
        }

        return numXmas;
    }

    public int checkForXmasP2(Point point) {
        int initX = point.getX();
        int initY = point.getY();

        // Edge checking
        if (initX == 0 || initY == 0 || initX == this.board.getFirst().size() - 1 || initY == this.board.size() - 1) {
//            System.out.printf("Skipping %s, edge\n", point);
            return 0;
        }


        // Checks diagonals to find "MAS" on both diagonals, in any order (M and S can be switched on diagonal)
        // M . M
        // . A .
        // S . S
        if (this.getChar(initX - 1, initY - 1) == 'M' && this.getChar(initX + 1, initY + 1) == 'S') {
            if (this.getChar(initX + 1, initY - 1) == 'M' && this.getChar(initX - 1, initY + 1) == 'S') {
                return 1;
            } else if (this.getChar(initX + 1, initY - 1) == 'S' && this.getChar(initX - 1, initY + 1) == 'M') {
                return 1;
            }
        } else if (this.getChar(initX - 1, initY - 1) == 'S' && this.getChar(initX + 1, initY + 1) == 'M') {
            if (this.getChar(initX + 1, initY - 1) == 'M' && this.getChar(initX - 1, initY + 1) == 'S') {
                return 1;
            } else if (this.getChar(initX + 1, initY - 1) == 'S' && this.getChar(initX - 1, initY + 1) == 'M') {
                return 1;
            }
        }

        return 0;
    }

    public char getChar(int x, int y) {
        return this.board.get(y).get(x);
    }

    public void printBoardInformation() {
        if (this.board.size() == 0) {
            System.out.println("Board empty");
            return;
        }

        System.out.printf("\nX Size: %d\n", this.board.getFirst().size());
        System.out.printf("Y Size: %d\n", this.board.size());
        this.printBoard();
    }

    public void printBoard() {
        if (this.board.isEmpty()) {
            System.out.println("Board empty");
            return;
        }

        for (ArrayList<Character> row : this.board) {
            System.out.println(row);
        }
        System.out.println();
    }
}
