package bmstu.student.rybkin.lab1application.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person {

    private @Id @GeneratedValue long id;

    @Column(nullable = false, length = 32)
    private String name = null;

    @Column
    private Integer age = null;

    @Column
    private String address = null;

    @Column
    private String work = null;

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

    public void setId(long id) {

        this.id = id;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setAge(Integer age) {

        this.age = age;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public void setWork(String work) {

        this.work = work;

    }

    public Person() {}

    public Person(String name, Integer age, String address, String work) {

        this.name = name;
        this.age = age;
        this.address = address;
        this.work = work;

    }

    public Person(long id, String name, Integer age, String address, String work)
    {

        this(name, age, address, work);
        this.id = id;

    }

    @Override
    public boolean equals(Object comp) {

        if (this == comp) {

            return true;

        }

        if (!(comp instanceof Person)) {

            return false;

        }

        Person person = (Person) comp;

        return (this.id == person.id) &&
                Objects.equals(this.name, person.name) &&
                Objects.equals(this.age, person.age) &&
                Objects.equals(this.address, person.address) &&
                Objects.equals(this.work, person.work);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.id, this.name, this.age, this.address, this.work);

    }

}
