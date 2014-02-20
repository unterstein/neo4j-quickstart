package info.unterstein.neo4j.quickstart;

import info.unterstein.neo4j.quickstart.Neo4JServiceProvider;
import info.unterstein.neo4j.quickstart.model.Organisation;
import info.unterstein.neo4j.quickstart.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    User god = createUser("God", null, null);

    Organisation orga1 = createOrganisation("Orga1", null, god);
    Organisation sub1 = createOrganisation("Orga1 - Sub1", orga1, god);
    Organisation sub2 = createOrganisation("Orga1 - Sub2", orga1, god);
    Organisation sub2sub1 = createOrganisation("Orga1 - Sub2 - Sub 1", sub2, god);

    User user = createUser("User1 -> Orga1 - Sub1", sub1, god);
    // expected:
    // Orga1 - Sub2
    // Orga1 - Sub2 - Sub 1
    List<Organisation> organisationsForUser = provider.organisationRepository.findOrganisationsForUser(user);
    Assert.assertEquals(1, organisationsForUser.size());
  }

  private Organisation createOrganisation(String name, Organisation parent, User author) {
    Organisation result = new Organisation();
    result.name = name;
    result.parent = parent;
    result.author = author;
    provider.organisationRepository.save(result);
    return result;
  }

  private User createUser(String name, Organisation organisation, User author) {
    User result = new User();
    result.name = name;
    result.organisation = organisation;
    result.author = author;
    provider.userRepository.save(result);
    return result;
  }
}
