package UDP;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;
    private static final long serialVersionUID = 20151107;

    public Customer(String id, String code, String name, String dayOfBirth, String username) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = username;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", code=" + code + ", name=" + name + ", dayOfBirth=" + dayOfBirth + ", username=" + userName + '}';
    }
    
    public void normalize(){
        normalizeName();
        normalizeDOB();
    }
    
    private void normalizeName(){
        String[] tokens = this.name.split("\\s+");
        
        StringBuilder normalized = new StringBuilder();
        StringBuilder genUsername = new StringBuilder();
        
        normalized
                .append(tokens[tokens.length - 1].toUpperCase())
                .append(", ");
        
        for (int i = 0; i < tokens.length - 1; i++){
            String token = tokens[i];
            
            normalized.append(Character.toUpperCase(token.charAt(0)));
            genUsername.append(Character.toLowerCase(token.charAt(0)));
            
            for (int j = 1; j < tokens[i].length(); j++){
                normalized.append(Character.toLowerCase(token.charAt(j)));
            }
            
            if (i < tokens.length - 1 - 1) normalized.append(" ");
        }
        
        genUsername.append(tokens[tokens.length - 1].toLowerCase());
        
        this.name = normalized.toString();
        this.userName = genUsername.toString();
    }
    
    private void normalizeDOB(){
        String[] tokens = this.dayOfBirth.split("-");

        this.dayOfBirth = tokens[1] + "/" + tokens[0] + "/" + tokens[2];
    }
}
