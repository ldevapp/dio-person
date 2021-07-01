package app.ldev.dio.person.personapi.service;

import app.ldev.dio.person.personapi.dto.MessageResponseDTO;
import app.ldev.dio.person.personapi.entity.Person;
import app.ldev.dio.person.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savePerson.getId())
                .build();
    }
}
