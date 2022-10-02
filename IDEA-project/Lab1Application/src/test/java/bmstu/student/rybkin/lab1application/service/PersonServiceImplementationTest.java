package bmstu.student.rybkin.lab1application.service;

import bmstu.student.rybkin.lab1application.jpa.Person;
import bmstu.student.rybkin.lab1application.jpa.PersonRepo;
import bmstu.student.rybkin.lab1application.models.PersonRequest;
import bmstu.student.rybkin.lab1application.models.PersonResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplementationTest {
    @Autowired
    PersonRepo personRepo;

    @Autowired
    PersonService personService;

    @Test
    void getPersons() {
        Random rand = new Random();
        int personCount = rand.nextInt(5) + 2;
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < personCount; i++) {

            persons.add(createRandomPerson());

        }
        personRepo.saveAll(persons);
        List<PersonResponse> responses = personService.getPersons();
        boolean ok = true;
        for (Person person : persons) {

            if (!responses.contains(new PersonResponse(person))) {

                ok = false;

            }

        }
        assertTrue(ok);

    }

    @RepeatedTest(5)
    void postPerson() {

        Person person = createRandomPerson();
        long got = personService.postPerson(new PersonRequest(person));
        Person posted = personRepo.findById(got).orElse(null);
        person.setId(got);
        assertEquals(person, posted);

    }

    @RepeatedTest(5)
    void getPerson() {

        Person saved = personRepo.save(createRandomPerson());
        PersonResponse personResponse = personService.getPerson(saved.getId());
        assertEquals(new PersonResponse(saved), personResponse);

    }

    @RepeatedTest(5)
    void deletePerson() {

        Person saved = personRepo.save(createRandomPerson());
        long savedId = saved.getId();
        personService.deletePerson(savedId);
        assertNull(personRepo.findById(savedId).orElse(null));

    }

    @RepeatedTest(5)
    void patchPerson() {

        Person saved = personRepo.save(createRandomPerson());
        PersonRequest request = buildRandomPatchRequest();
        String name = request.getName();
        Integer age = ((request.getAge() == null) ? saved.getAge() : request.getAge());
        String address = ((request.getAddress() == null) ? saved.getAddress() : request.getAddress());
        String work = ((request.getWork() == null) ? saved.getWork() : request.getWork());
        System.out.println(request.getName());
        System.out.println(request.getAge());
        System.out.println(request.getAddress());
        System.out.println(request.getWork());
        System.out.println("---------------------");
        System.out.println(name);
        System.out.println(age);
        System.out.println(address);
        System.out.println(work);
        PersonResponse expectedResponse = new PersonResponse(saved.getId(), name, age, address, work);
        PersonResponse receivedResponse = personService.patchPerson(saved.getId(), request);
        assertEquals(expectedResponse, receivedResponse);

    }

    private String randomizeName() {

        int lowercaseA = 'a';
        int lowercaseZ = 'z';
        int uppercaseA = 'A';
        int uppercaseZ = 'Z';
        Random rand = new Random();
        int nameLength = rand.nextInt(20) + 11;

        return rand.ints(uppercaseA, lowercaseZ + 1)
                .filter(i -> (i >= uppercaseA && i <= uppercaseZ) || (i >= lowercaseA && i <= lowercaseZ))
                .limit(nameLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    private String randomizeAddress() {

        int lowercaseA = 'a';
        int lowercaseZ = 'z';
        int uppercaseA = 'A';
        int uppercaseZ = 'Z';
        Random rand = new Random();
        int addressLength = rand.nextInt(17) + 10;

        return rand.ints(uppercaseA, lowercaseZ + 1)
                .filter(i -> (i >= uppercaseA && i <= uppercaseZ) || (i >= lowercaseA && i <= lowercaseZ))
                .limit(addressLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    private String randomizeWork() {

        int lowercaseA = 'a';
        int lowercaseZ = 'z';
        int uppercaseA = 'A';
        int uppercaseZ = 'Z';
        Random rand = new Random();
        int workLength = rand.nextInt(17) + 10;

        return rand.ints(uppercaseA, lowercaseZ + 1)
                .filter(i -> (i >= uppercaseA && i <= uppercaseZ) || (i >= lowercaseA && i <= lowercaseZ))
                .limit(workLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    private PersonRequest buildRandomPatchRequest() {

        Random rand = new Random();
        String name = randomizeName();
        Integer age;
        String address;
        String work;
        int roll = rand.nextInt(10);
        if (roll % 2 == 0) {

            age = rand.nextInt(101);

        } else {

            age = null;

        }
        roll = rand.nextInt(10);
        if (roll >= 5) {

            address = randomizeAddress();

        } else {

            address = null;

        }
        if (roll % 2 == 0) {

            work = randomizeWork();

        } else {

            work = null;

        }
        return new PersonRequest(name, age, address, work);

    }

    private Person createRandomPerson() {

        Random rand = new Random();
        String name = randomizeName();
        int age = rand.nextInt(101);
        String address = randomizeAddress();
        String work = randomizeWork();
        return new Person(name, age, address, work);

    }
}