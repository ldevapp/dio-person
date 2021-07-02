package app.ldev.dio.person.personapi.service;

import app.ldev.dio.person.personapi.dto.request.PersonDTO;
import app.ldev.dio.person.personapi.dto.response.MessageResponseDTO;
import app.ldev.dio.person.personapi.entity.Person;
import app.ldev.dio.person.personapi.dto.mapper.PersonMapper;
import app.ldev.dio.person.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savePerson.getId())
                .build();
    }
}
