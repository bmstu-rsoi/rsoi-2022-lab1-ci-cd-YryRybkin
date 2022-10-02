package bmstu.student.rybkin.lab1application.models;

import bmstu.student.rybkin.lab1application.jpa.Person;

import java.util.Objects;

public class PersonResponse {

    private final long id;
    private final String name;
    private final Integer age;
    private final String address;
    private final String work;

    public long getId() {

        return this.id;

    }

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

    public PersonResponse(long id, String name, Integer age, String address, String work) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.work = work;

    }

    public PersonResponse(Person person)
    {

        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
        this.address = person.getAddress();
        this.work = person.getWork();

    }

    @Override
    public boolean equals(Object comp) {

        if (this == comp) {

            return true;

        }

        if (!(comp instanceof PersonResponse)) {

            return false;

        }

        PersonResponse personResponse = (PersonResponse) comp;

        return id == personResponse.id &&
                Objects.equals(this.name, personResponse.name) &&
                Objects.equals(this.age, personResponse.age) &&
                Objects.equals(this.work, personResponse.work) &&
                Objects.equals(this.address, personResponse.address);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.id, this.name, this.age, this.work, this.address);

    }

}
