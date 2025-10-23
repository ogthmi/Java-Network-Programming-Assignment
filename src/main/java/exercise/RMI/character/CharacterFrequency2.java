package exercise.RMI.character;

import RMI.CharacterService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class CharacterFrequency2 extends RMIBase<CharacterService> {
    public CharacterFrequency2(Registry registry) throws Exception {
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
        return "vWQlQj2c";
    }

    @Override
    public void execute() throws Exception {
        String serverResponse = service.requestCharacter(studentID, qCode);
        System.out.println("Server response: " + serverResponse);

        LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();

        for (Character c : serverResponse.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        String result = "{" + freq.entrySet().stream().map(entry ->
                        String.format("\"%c\": %d", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ")) + "}";

        System.out.println("Freq: " + result);
        service.submitCharacter(studentID, qCode, result);
    }
}
