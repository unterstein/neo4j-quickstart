package info.unterstein.neo4j.quickstart.repository;


import info.unterstein.neo4j.quickstart.model.Organisation;
import info.unterstein.neo4j.quickstart.model.User;
import info.unterstein.neo4j.quickstart.relation.Relations;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface OrganisationRepository extends GraphRepository<Organisation> {

  @Query("START user=node({0})  MATCH user-[:" + Relations.ORGANISATION_USER + "]->organisation<-[:" + Relations.ORGANISATION_ORGANISATION + "*0..]-children RETURN children")
  public List<Organisation> findOrganisationsForUser(User user);
}
