import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File inputFile = new File(FILENAME);
        int part1Answer = 0;
        int part2Answer = 0;

        ArrayList<ArrayList<Character>> tempBoard = new ArrayList<>();
        ArrayList<Point> xLocations = new ArrayList<>();
        ArrayList<Point> aLocations = new ArrayList<>();

        try {
            Scanner inputScanner = new Scanner(inputFile);

            while (inputScanner.hasNextLine()) {
                String row = inputScanner.nextLine();
                populateRow(row, tempBoard, xLocations, aLocations);
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
            System.exit(1);
        }

        Board board = new Board(tempBoard);
//        board.printBoardInformation();
//        System.out.println(aLocations);

        for (Point point : xLocations) {
            part1Answer += board.checkForXmasP1(point);
        }

        for (Point point : aLocations) {
            part2Answer += board.checkForXmasP2(point);
        }

        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }

    public static void populateRow(String row, ArrayList<ArrayList<Character>> board, ArrayList<Point> xLocations, ArrayList<Point> aLocations) {
        ArrayList<Character> rowList = new ArrayList<>();
        char[] rowSplit = row.toCharArray();

        for (int i = 0; i < rowSplit.length; i++) {
            char nextChar = rowSplit[i];
            rowList.add(nextChar);
            if (nextChar == 'X') {
                int xPos = i;
                int yPos = board.size();
                Point newXPoint = new Point(xPos, yPos);
                xLocations.add(newXPoint);
            } else if (nextChar == 'A') {
                int xPos = i;
                int yPos = board.size();
                Point newAPoint = new Point(xPos, yPos);
                aLocations.add(newAPoint);
            }
        }

        board.add(rowList);
    }
}
