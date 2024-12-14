import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File inputFile = new File(FILENAME);
        int part1Answer = 0;
        int part2Answer = 0;

        try {
            ArrayList<ArrayList<Character>> tempBoard = new ArrayList<>();
            Scanner inputScanner = new Scanner(inputFile);

            while (inputScanner.hasNextLine()) {
                System.out.println("Hi");
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
            System.exit(1);
        }

        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }
}
