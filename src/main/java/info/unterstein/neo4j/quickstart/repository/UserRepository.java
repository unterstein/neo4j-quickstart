package info.unterstein.neo4j.quickstart.repository;


import info.unterstein.neo4j.quickstart.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {
}
