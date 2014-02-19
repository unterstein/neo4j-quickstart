package info.unterstein.neo4j.quickstart.model;

import info.unterstein.neo4j.quickstart.relation.Relations;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
@TypeAlias("User")
public class User {
  @GraphId
  public Long pk;

  public String name;

  @RelatedTo(type = Relations.ORGANISATION_USER)
  public Organisation organisation;
}
