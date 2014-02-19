package info.unterstein.neo4j.quickstart;

import info.unterstein.neo4j.quickstart.repository.OrganisationRepository;
import info.unterstein.neo4j.quickstart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4JServiceProvider {

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public OrganisationRepository organisationRepository;
}
