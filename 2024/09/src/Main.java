import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File inputFile = new File(FILENAME);
        long part1Answer;
        long part2Answer;
        String input = null;

        try {
            Scanner inputScanner = new Scanner(inputFile);
            input = inputScanner.nextLine();
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
            System.exit(1);
        }

        ArrayList<String> diskMap = new ArrayList<>();
        ArrayList<String> diskBlocks = new ArrayList<>();
        ArrayList<String> diskBlocksP2 = new ArrayList<>(diskBlocks);

        parseInput(input, diskMap);
        convertToDisk(diskMap, diskBlocks);
        defrag(diskBlocks);
        part1Answer = calculateChecksum(diskBlocks);
        defragPart2(diskBlocksP2);
        part2Answer = calculateChecksum(diskBlocksP2);

        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }

    public static void parseInput(String input, ArrayList<String> diskMap) {
        char[] splitInput = input.toCharArray();

        for (char nextChar : splitInput) {
            diskMap.add(String.valueOf(nextChar));
        }
    }

    public static void convertToDisk(ArrayList<String> diskMap, ArrayList<String> diskBlocks) {
        boolean isFile = true;
        int currentFile = 0;

        for (String s : diskMap) {
            int size = Integer.parseInt(s);

            for (int i = 0; i < size; i++) {
                if (isFile) {
                    diskBlocks.add(String.valueOf(currentFile));
                } else {
                    diskBlocks.add(".");
                }
            }

            if (isFile) {
                currentFile++;
            }
            isFile = !isFile;
        }
    }

    public static void defrag(ArrayList<String> diskMap) {
        int[] coordinates = getNextCoordinates(diskMap, 0, diskMap.size() - 1);

        while (coordinates[0] != -1 || coordinates[1] != -1) {
//            System.out.printf("Next: (%s, %s)\n", coordinates[0], coordinates[1]);
            diskMap.set(coordinates[0], diskMap.get(coordinates[1]));
            diskMap.set(coordinates[1], ".");
            coordinates = getNextCoordinates(diskMap, coordinates[0], coordinates[1]);
        }
    }

    public static void defragPart2(ArrayList<String> diskMap) {
        int[] coordinates = getNextCoordinates(diskMap, 0, diskMap.size() - 1);

        while (coordinates[0] != -1 || coordinates[1] != -1) {
//            System.out.printf("Next: (%s, %s)\n", coordinates[0], coordinates[1]);
            diskMap.set(coordinates[0], diskMap.get(coordinates[1]));
            diskMap.set(coordinates[1], ".");
            coordinates = getNextCoordinates(diskMap, coordinates[0], coordinates[1]);
        }
    }

    public static int[] getNextCoordinates(ArrayList<String> diskMap, int leftIndex, int rightIndex) {
        int newLeft = -1;
        while (leftIndex < rightIndex) {
            if (diskMap.get(leftIndex).equals(".")) {
                newLeft = leftIndex;
                break;
            }
            leftIndex++;
        }

        int newRight = -1;
        while (rightIndex > leftIndex) {
            if (!diskMap.get(rightIndex).equals(".")) {
                newRight = rightIndex;
                break;
            }
            rightIndex--;
        }

        return new int[] {newLeft, newRight};
    }

    public static long calculateChecksum(ArrayList<String> diskMap) {
        long checkSum = 0;

        for (int i = 0; i < diskMap.size() - 1; i++) {
            if (diskMap.get(i).equals(".")) { continue; }

            checkSum += i * Long.parseLong(diskMap.get(i));
        }

        return checkSum;
    }
}