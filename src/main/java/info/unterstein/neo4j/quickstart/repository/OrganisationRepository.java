package info.unterstein.neo4j.quickstart.repository;


import info.unterstein.neo4j.quickstart.model.Organisation;
import info.unterstein.neo4j.quickstart.model.User;
import info.unterstein.neo4j.quickstart.relation.Relations;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface OrganisationRepository extends GraphRepository<Organisation> {

  // first thought
  // @Query("START user=node({0}) MATCH user-[:" + Relations.ORGANISATION_USER + "]-organisation RETURN organisation UNION MATCH user-[:" + Relations.ORGANISATION_USER + "]-organisation<-[:" + Relations.ORGANISATION_ORGANISATION + "*]-children RETURN children as organisation")
  // but each UNION starts a new cypher, therefore this is correct:
  // @Query("START user=node({0}) MATCH user-[:" + Relations.ORGANISATION_USER + "]-organisation RETURN organisation UNION START user=node({0}) MATCH user-[:" + Relations.ORGANISATION_USER + "]-organisation<-[:" + Relations.ORGANISATION_ORGANISATION + "*]-children RETURN children as organisation")
  // but far to complicated, therefore use this fancy cypher: (thanks to michael hunger!)
  @Query("START user=node({0})  MATCH user-[:" + Relations.ORGANISATION_USER + "]->organisation<-[:" + Relations.ORGANISATION_ORGANISATION + "*0..]-children RETURN children")
  public List<Organisation> findOrganisationsForUser(User user);
}
