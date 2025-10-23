package exercise.RMI.character;

import RMI.CharacterService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class RomanToDecConverter extends RMIBase<CharacterService> {
    public RomanToDecConverter(Registry registry) throws Exception {
        super(registry);
    }

    @Override
    protected Class<CharacterService> getServiceClass() {
        return CharacterService.class;
    }

    @Override
    protected String getStudentID() {
        return "B22DCCN541";
    }

    @Override
    protected String getQCode() {
        return "wXRmRk3d";
    }

    @Override
    public void execute() throws Exception {
        String serverResponse = service.requestCharacter(studentID, qCode);
        System.out.println("Roman: " + serverResponse);

        String dec = convertRomanToDec(serverResponse);
        System.out.println("Dec: " + dec);

        service.submitCharacter(studentID, qCode, dec);
    }

    protected String convertRomanToDec(String roman){
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
}
