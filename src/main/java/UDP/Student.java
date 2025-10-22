package UDP;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Student implements Serializable {
    private String id, code, email, name;
    private static final long serialVersionUID = 20171107;

    public Student(String id, String code, String email, String name) {
        this.id = id;
        this.code = code;
        this.email = email;
        this.name = name;
    }

    public Student(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void normalizeName() {
        this.name = Arrays.stream(this.name.split("\\s+"))
                .map(token ->
                        token.substring(0, 1).toUpperCase() +
                        token.substring(1).toLowerCase()
                )
                .collect(Collectors.joining(" "));
    }

    public void setEmail(){
        String[] tokens = this.name.toLowerCase().split("\\s+");

        this.email = tokens[tokens.length - 1] + Arrays.stream(tokens, 0, tokens.length - 1)
                .map(token -> token.substring(0, 1))
                .collect(Collectors.joining()) + "@ptit.edu.vn";
    }
}
