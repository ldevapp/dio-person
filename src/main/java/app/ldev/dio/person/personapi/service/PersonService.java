package app.ldev.dio.person.personapi.service;

import app.ldev.dio.person.personapi.dto.request.PersonDTO;
import app.ldev.dio.person.personapi.dto.response.MessageResponseDTO;
import app.ldev.dio.person.personapi.entity.Person;
import app.ldev.dio.person.personapi.dto.mapper.PersonMapper;
import app.ldev.dio.person.personapi.exceptions.PersonalNotFoundException;
import app.ldev.dio.person.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonalNotFoundException {
        Person person = verifyUfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonalNotFoundException {
        Person person = verifyUfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyUfExists(Long id) throws PersonalNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonalNotFoundException(id));
    }
}
