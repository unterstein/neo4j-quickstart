package de.micromata.freestyle.neo4j;

import info.unterstein.neo4j.quickstart.Neo4JServiceProvider;
import info.unterstein.neo4j.quickstart.model.Organisation;
import info.unterstein.neo4j.quickstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class Neo4JTest {

  @Autowired
  private Neo4JServiceProvider provider;

  @Before
  public void setUp() {
    provider.organisationRepository.deleteAll();
    provider.userRepository.deleteAll();
  }

  @Test
  public void testRelations() {
    Organisation orga1 = createOrganisation("Orga1", null);
    Organisation sub1 = createOrganisation("Orga1 - Sub1", orga1);
    Organisation sub2 = createOrganisation("Orga1 - Sub2", orga1);
    Organisation sub2sub1 = createOrganisation("Orga1 - Sub2 - Sub 1", sub2);
    User user1 = createUser("User1 -> Orga1 - Sub1", sub1);
    User user2 = createUser("User1 -> Orga1 - Sub2", sub2);
    for (Organisation o : provider.organisationRepository.findOrganisationsForUser(user2)) {
      System.out.println(o.name);
    }
  }

  private Organisation createOrganisation(String name, Organisation parent) {
    Organisation result = new Organisation();
    result.name = name;
    result.parent = parent;
    provider.organisationRepository.save(result);
    return result;
  }

  private User createUser(String name, Organisation organisation) {
    User result = new User();
    result.name = name;
    result.organisation = organisation;
    provider.userRepository.save(result);
    return result;
  }
}
