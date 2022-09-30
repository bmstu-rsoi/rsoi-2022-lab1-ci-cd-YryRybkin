package bmstu.student.rybkin.lab1application.service;

import bmstu.student.rybkin.lab1application.jpa.Person;
import bmstu.student.rybkin.lab1application.jpa.PersonRepo;
import bmstu.student.rybkin.lab1application.models.PersonRequest;
import bmstu.student.rybkin.lab1application.models.PersonResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonRepo personRepo;

    public PersonServiceImplementation(PersonRepo personRepo) {

        this.personRepo = personRepo;

    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonResponse> getPersons() {

        return personRepo.findAll().stream().map(
                temp -> buildPersonResponse(temp) ).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public long postPerson(PersonRequest personRequest) {

        Person person = new Person(personRequest.getName(), personRequest.getAge(),
                personRequest.getAddress(), personRequest.getWork());
        Person posted = personRepo.save(person);
        return posted.getId();

    }

    @Transactional(readOnly = true)
    @Override
    public PersonResponse getPerson(long id) {

        Person person = personRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Person with id: %d not found", id)) );
        return buildPersonResponse(person);

    }

    @Transactional
    @Override
    public void deletePerson(long id) {

        personRepo.deleteById(id);

    }

    @Transactional
    @Override
    public PersonResponse patchPerson(long id, PersonRequest personRequest) {

        Person person = personRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Person with id: %d not found", id)) );
        person.setName(personRequest.getName());
        if (personRequest.getAge() != null)
            person.setAge(personRequest.getAge());
        if (personRequest.getAddress() != null)
            person.setAddress(personRequest.getAddress());
        if (personRequest.getWork() != null)
            person.setWork(personRequest.getWork());
        personRepo.save(person);
        return buildPersonResponse(person);

    }

    private PersonResponse buildPersonResponse(Person person) {

        return new PersonResponse(person.getId(),
                person.getName(), person.getAge(),
                person.getAddress(), person.getWork());

    }

}
