package checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleIO {
    static String convertRomanToDec(String roman){
        HashMap<Character, Integer> romanMap = new HashMap<>(Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        ));

        int dec = romanMap.get(roman.charAt(roman.length() - 1));
        for (int i = roman.length() - 2; i >= 0; i--){
            int curr = romanMap.get(roman.charAt(i));
            int next = romanMap.get(roman.charAt(i + 1));

            if (curr >= next) {
                dec += curr;
            }
            else {
                dec -= curr;
            }
        }

        return String.valueOf(dec);
    }

    public static void main(String[] args) {
        String roman = "MMXXV";
        System.out.println(convertRomanToDec(roman));
    }

}
