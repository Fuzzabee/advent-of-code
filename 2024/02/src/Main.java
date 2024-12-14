import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";
    final static int MINIMUM_DIFFERENCE = 1;
    final static int MAXIMUM_DIFFERENCE = 3;

    public static void main(String[] args) {
        File f_input = new File(FILENAME);
        int part1Answer = 0;
        int part2Answer = 0;

        try {
            Scanner s_input = new Scanner(f_input);

            while (s_input.hasNextLine()) {
                String nextLine = s_input.nextLine();
                ArrayList<Integer> report = convertReport(nextLine);
                part1Answer += checkReport(report);
                part2Answer += checkReportPart2(report);
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
        }

        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }

    public static ArrayList<Integer> convertReport(String report) {
        String[] split = report.split(" ");
        ArrayList<Integer> convertedReport = new ArrayList<>();
        for (String digit : split) {
            int convertedDigit = Integer.parseInt(digit);
            convertedReport.add(convertedDigit);
        }

        return convertedReport;
    }

    public static int checkReport(ArrayList<Integer> report) {
        if (report.size() < 2) {
            return 0;
        }

        boolean isDecreasing = report.getFirst() > report.get(1);

        for (int i = 0; i < report.size() - 1; i++) {
            int firstNum = report.get(i);
            int secondNum = report.get(i + 1);
            int difference = firstNum - secondNum;
            int totalChange = Math.abs(difference);

            if (isDecreasing && difference < 0 ||
                !isDecreasing && difference > 0) {
//                System.out.printf("isDec: %b, diff: %d\n", isDecreasing, difference);
                return 0;
            }

            if (totalChange > MAXIMUM_DIFFERENCE || totalChange < MINIMUM_DIFFERENCE) {
//                System.out.printf("totalChange: %d\n", totalChange);
                return 0;
            }
        }

        return 1;
    }

    // Lazily implemented, should check for bad indices before
    public static int checkReportPart2(ArrayList<Integer> report) {
        if (checkReport(report) == 1) {
            return 1;
        }

        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> test = new ArrayList<>(report);
            test.remove(i);
            if (checkReport(test) == 1) {
                return 1;
            }
        }

        return 0;
    }
}
