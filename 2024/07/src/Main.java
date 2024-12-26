import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File inputFile = new File(FILENAME);
        long part1Answer = 0;
        long part2Answer = 0;

        try {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNext()) {
                part1Answer += bridgeRepairPart1(inputScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
            System.exit(1);
        }


        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }

    // String comes in as:
    // "targetValue: n0 n1 n2 ... n"
    public static int bridgeRepairPart1(String lineInput) {
        String[] splitInput = lineInput.split(" ");
        int targetValue = Integer.parseInt(splitInput[0].substring(0, splitInput[0].length() - 1));
//        System.out.printf("Testing %d\n", targetValue);
        boolean found = searchForValidOperators(new ArrayList<>(), targetValue, Arrays.stream(splitInput, 1, splitInput.length).toArray(String[]::new));

        if (found) {
            return targetValue;
        }

        return 0;
    }

    public static boolean searchForValidOperators(ArrayList<Integer> currentValues, int targetValue, String[] rest) {
//        System.out.println(currentValues);
        if (rest.length == 0) {
            return currentValues.contains(targetValue);
        }

        int nextValue = Integer.parseInt(rest[0]);

        if (currentValues.isEmpty()) {
            currentValues.add(nextValue);
            return searchForValidOperators(currentValues, targetValue, Arrays.stream(rest, 1, rest.length).toArray(String[]::new));
        }

        ArrayList<Integer> newValues = new ArrayList<>();
        for (Integer currentValue : currentValues) {
            int nextAdd = currentValue + nextValue;
            int nextMult = currentValue * nextValue;
            if (nextAdd <= targetValue) { newValues.add(nextAdd); }
            if (nextMult <= targetValue) { newValues.add(nextMult); }
        }

        return searchForValidOperators(newValues, targetValue, Arrays.stream(rest, 1, rest.length).toArray(String[]::new));
    }
}