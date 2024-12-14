import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String FILENAME = "test.txt";

    public static void main(String[] args) {
        File f_input = new File(FILENAME);
        int part1Answer = 0;
        int part2Answer = 0;
        List<String> matchesPart1 = null;
        List<String> matchesPart2 = null;

        try {
            Scanner s_input = new Scanner(f_input);
            boolean active = true;

            while (s_input.hasNextLine()) {
                String nextLine = s_input.nextLine();
                matchesPart1 = new ArrayList<>(gatherMatches(nextLine, 1));
                matchesPart2 = new ArrayList<>(gatherMatches(nextLine, 2));

                for (String match: matchesPart1) {
                    part1Answer += parseAndMultiply(match);
                }

                for (String match : matchesPart2) {
                    if (match.equals("do()")) {
                        active = true;
                        continue;
                    }

                    if (match.equals("don't()")) {
                        active = false;
                        continue;
                    }

                    if (active) {
                        part2Answer += parseAndMultiply(match);
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Could not open %s\n", FILENAME);
            System.exit(1);
        }

        System.out.printf("Part 1: %d\n", part1Answer);
        System.out.printf("Part 2: %d\n", part2Answer);
    }

    public static List<String> gatherMatches(String line, int part) {
        Pattern pattern = null;

        if (part == 1) {
            pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        } else {
            pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
        }
        Matcher matcher = pattern.matcher(line);
        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }

    public static int parseAndMultiply(String mul) {
        String sub = mul.substring(4, mul.length() - 1);
        String[] split = sub.split(",");

        int firstNum = Integer.parseInt(split[0]);
        int secondNum = Integer.parseInt(split[1]);

        return firstNum * secondNum;
    }
}
