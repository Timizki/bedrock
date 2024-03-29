package net.vksn.bedrock.dao.hibernate;

import java.util.List;
import static org.junit.Assert.*;
import javax.persistence.EntityNotFoundException;

import net.vksn.bedrock.AbstractDatabaseTestCase;
import net.vksn.bedrock.dao.UserDAO;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.UserQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(locations = {
		"classpath:net/vksn/bedrock/dataAccessContext.xml",
		"classpath:net/vksn/bedrock/dataSourceContext.xml",
		"classpath:net/vksn/bedrock/transactionContext.xml"
		})
public class UserServiceTest extends AbstractDatabaseTestCase {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void getUser() throws Exception {
		User user = userDAO.getUserByUsername("Timii");
		assertEquals("Timii", user.getUsername());
	}
	
	@Test
	public void getUser_byQuery() throws EntityNotFoundException {
		UserQuery query = new UserQuery();
		query.setId(1);
		List<User> users = (List<User>)userDAO.getByQuery(query);
		assertEquals(1, users.size());
	}
}
