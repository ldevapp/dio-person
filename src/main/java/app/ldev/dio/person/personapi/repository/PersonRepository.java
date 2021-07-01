package app.ldev.dio.person.personapi.repository;

import app.ldev.dio.person.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
