package exercise.RMI.character;

import RMI.CharacterService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharacterFrequency extends RMIBase<CharacterService> {
    public CharacterFrequency(Registry registry) throws Exception {
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
        return "zr60yKMl";
    }

    @Override
    public void execute() throws Exception {
        String serverResponse = service.requestCharacter(studentID, qCode);
        System.out.println("Server response: " + serverResponse);

        LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();
        for (Character c: serverResponse.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Character, Integer> entry: freq.entrySet()){
            builder.append(entry.getKey()).append(entry.getValue());
        }

        System.out.println("Freq: " + builder);

        service.submitCharacter(studentID, qCode, builder.toString());
    }
}
