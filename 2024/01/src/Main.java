import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File f_input = new File(FILENAME);
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        HashMap<Integer, Integer> h_rightList = new HashMap<>();

        try {
            Scanner s_input = new Scanner(f_input);

            while (s_input.hasNextLine()) {
                String nextLine = s_input.nextLine();
                String[] splitLine = nextLine.split("   ");
                int leftNumber = Integer.parseInt(splitLine[0]);
                int rightNumber = Integer.parseInt(splitLine[1]);

                leftList.add(leftNumber);
                rightList.add(rightNumber);

                if (!h_rightList.containsKey(rightNumber)) {
                    h_rightList.put(rightNumber, 1);
                } else {
                    int updatedValue = h_rightList.get(rightNumber) + 1;
                    h_rightList.put(rightNumber, updatedValue);
                }
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
        }

        System.out.println("Read all numbers...");

        Collections.sort(leftList);
        Collections.sort(rightList);

        int part1Sum = 0;
        int part2Sum = 0;
        for (int i = 0; i < leftList.size(); i++) {
            int lValue = leftList.get(i);
            int rValue = rightList.get(i);
            int difference = Math.abs(lValue - rValue);
            part1Sum += difference;

            if (h_rightList.containsKey(lValue)) {
                int timesAppeared = h_rightList.get(lValue);
                int toAdd = lValue * timesAppeared;
                part2Sum += toAdd;
            }
        }

        System.out.printf("Part 1: %d\n", part1Sum);
        System.out.printf("Part 2: %d\n", part2Sum);
    }
}
