package info.unterstein.neo4j.quickstart.model;

import info.unterstein.neo4j.quickstart.relation.Relations;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

@NodeEntity
@TypeAlias("Organisation")
public class Organisation extends AuthoredEntity {

  @RelatedTo(type = Relations.ORGANISATION_ORGANISATION)
  public Organisation parent;

  @RelatedTo(type = Relations.ORGANISATION_ORGANISATION, direction = Direction.INCOMING)
  public Set<Organisation> children;

  @RelatedTo(type = Relations.ORGANISATION_USER, direction = Direction.INCOMING)
  public Set<User> user;

}
