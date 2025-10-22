package TCP;

import java.io.Serializable;

public class Address implements Serializable{
    private final int id;
    private final String code;
    private String addressLine;
    private final String city;
    private String postalCode;
    private static final long serialVersionUID = 20180801L;

    public Address(int id, String code, String addressLine, String city, String postalCode) {
        this.id = id;
        this.code = code;
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", code=" + code + ", addressLine=" + addressLine + ", city=" + city + ", postalCode=" + postalCode + '}';
    }
    
    public void normalize(){
        normalizeAddress();
        normalizePostalCode();
    }
    
    private void normalizeAddress(){
        String[] tokens = this.addressLine.split("\\s+");
        
        StringBuilder normalized = new StringBuilder();
        
        for (int i = 0; i < tokens.length; i++){
            String token = tokens[i];
            
            StringBuilder sbToken = new StringBuilder();
            sbToken.append(Character.toUpperCase(token.charAt(0)));
            
            for (int j = 1; j < token.length(); j++){
                char c = token.charAt(j);
                if (Character.isAlphabetic(c)){
                    sbToken.append(Character.toLowerCase(c));
                }
                else if (Character.isDigit(c)){
                    sbToken.append(c);
                }
            }
            
            normalized.append(sbToken);
            
            if (i < tokens.length - 1){
                normalized.append(" ");
            }
        }
        
        this.addressLine = normalized.toString();
    }
    
    private void normalizePostalCode(){
        StringBuilder normalized = new StringBuilder();
        
        for (Character c: this.postalCode.toCharArray()){
            if (Character.isDigit(c) || c == '-'){
                normalized.append(c);
            }
        }
        
        this.postalCode = normalized.toString();
    }
}
