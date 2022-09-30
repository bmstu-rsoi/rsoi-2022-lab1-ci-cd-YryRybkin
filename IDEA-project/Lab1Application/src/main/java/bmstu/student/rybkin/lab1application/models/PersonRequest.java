package bmstu.student.rybkin.lab1application.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class PersonRequest {

    @NotNull
    @Size(max=32)
    private final String name;

    @Min(0)
    private final Integer age;
    private final String address;
    private final String work;

    public String getName() {

        return this.name;

    }

    public Integer getAge() {

        return this.age;

    }

    public String getAddress() {

        return this.address;

    }

    public String getWork() {

        return this.work;

    }

    public PersonRequest(String name, Integer age, String address, String work) {

        this.name = name;
        this.age = age;
        this.address = address;
        this.work = work;

    }

    @Override
    public boolean equals(Object comp) {

        if (this == comp) {

            return true;

        }

        if (!(comp instanceof PersonRequest)) {

            return false;

        }

        PersonRequest personRequest = (PersonRequest) comp;

        return Objects.equals(this.name, personRequest.name) &&
                Objects.equals(this.age, personRequest.age) &&
                Objects.equals(this.work, personRequest.work) &&
                Objects.equals(this.address, personRequest.address);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.name, this.age, this.work, this.address);

    }

}
