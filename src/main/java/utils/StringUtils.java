package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StringUtils {
    public static String[] splitByLine(String string) {
        return  string.split("\n");
    }

    public static ArrayList<Integer> extractIntegers(String string) {
        ArrayList<Integer> outputIntegers = new ArrayList<>();

        String[] splitStrings = string.split(";");

        for (String splitString : splitStrings) {
            try {
                int tmp = Integer.parseInt(splitString);
                outputIntegers.add(tmp);
            } catch (NumberFormatException nfe) {
                //pass
            }
        }
        return outputIntegers;
    }

    public static String parsefileToString(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream input = new FileInputStream(file);

        return new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining("\n"));

    }


}
