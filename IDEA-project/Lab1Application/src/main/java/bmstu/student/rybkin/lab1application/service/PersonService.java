package bmstu.student.rybkin.lab1application.service;

import bmstu.student.rybkin.lab1application.models.PersonRequest;
import bmstu.student.rybkin.lab1application.models.PersonResponse;

import java.util.List;

public interface PersonService {

    List<PersonResponse> getPersons();
    long postPerson(PersonRequest personRequest);
    PersonResponse getPerson(long id);
    void deletePerson(long id);
    PersonResponse patchPerson(long id, PersonRequest personRequest);

}
