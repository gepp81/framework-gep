package ar.com.gepp.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.gepp.framework.core.FrameWorkApplicationContext;
import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;
import ar.com.gepp.framework.persistence.dao.crud.GenericCrudHibernateDAO;
import ar.com.gepp.test.entities.TestEntity;

/**
 * 
 * @author gpidote
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-root-spring.xml" })
public class GenericCrudDAOTest {

	@Autowired
	private GenericCrudHibernateDAO<TestEntity> genericCrudDAOTestImpl;

	@Before
	public void before() {
		assertNotNull("No se inicializo el contexto", FrameWorkApplicationContext.getApplicationContext());
	}

	@Test
	public void createEntity() throws FrameworkPersistenceException {
		TestEntity testEntity = new TestEntity();
		testEntity.setName("AAA");
		genericCrudDAOTestImpl.create(testEntity);
	}

	@Test
	public void getById() throws FrameworkPersistenceException {
		TestEntity testEntity = genericCrudDAOTestImpl.getById(101L);
		Assert.assertEquals(testEntity.getName(), "BBB");
	}

	@Test(expected = FrameworkPersistenceException.class)
	public void getByIdNull() throws FrameworkPersistenceException {
		genericCrudDAOTestImpl.getById(null);
	}

	@Test
	public void getAll() throws FrameworkPersistenceException {
		List<TestEntity> entities = genericCrudDAOTestImpl.getAll();
		Assert.assertFalse(entities.isEmpty());
	}

	@Test
	public void updateEntity() throws FrameworkPersistenceException {
		TestEntity testEntity = new TestEntity();
		testEntity.setId(102L);
		testEntity.setName("CCC");
		genericCrudDAOTestImpl.update(testEntity);
		testEntity = genericCrudDAOTestImpl.getById(102L);
		Assert.assertEquals(testEntity.getName(), "CCC");
	}

	@Test
	public void deleteEntity() throws FrameworkPersistenceException {
		genericCrudDAOTestImpl.deleteById(103L);
	}

	@Test(expected = FrameworkPersistenceException.class)
	public void deleteEntityNullException() throws FrameworkPersistenceException {
		genericCrudDAOTestImpl.deleteById(500L);
	}

}
