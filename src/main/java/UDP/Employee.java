package UDP;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Employee implements Serializable {
    private String id, name, hireDate;
    private double salary;
    private static final long serialVersionUID = 20261107L;

    public Employee(String id, String name, String hireDate, double salary) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void normalize() {
        normalizeName();
        normalizeDate();
    }

    private void normalizeName() {
        this.name = Arrays.stream(this.name.split("\\s+"))
                .map(token ->
                        token.substring(0, 1).toUpperCase() +
                                token.substring(1).toLowerCase()
                )
                .collect(Collectors.joining(" "));
    }

    private void normalizeDate() {
        String[] tokens = this.hireDate.split("-");
        this.hireDate = tokens[2] + "/" + tokens[1] + "/" + tokens[0];

        int rate = 0;
        for (int i = 0; i < tokens[0].length(); i++) {
            rate += Integer.parseInt(tokens[0].substring(i, i + 1));
        }

        this.salary = (1 + 1.0 * rate / 100) * salary;
    }
}
