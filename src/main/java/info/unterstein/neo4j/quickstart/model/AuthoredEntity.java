package info.unterstein.neo4j.quickstart.model;

import info.unterstein.neo4j.quickstart.relation.Relations;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class AuthoredEntity {
  @GraphId
  public Long pk;

  public String name;

  @RelatedTo(type = Relations.ENTITY_AUTHOR)
  public User author;
}
