package ar.com.gepp.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.gepp.framework.business.bo.crud.GenericCrudBO;
import ar.com.gepp.framework.business.bo.crud.impl.GenericCrudBOImpl;
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
public class GenericCrudBOTest {

	private static final String GENERICDAO = "genericDAO";

	@Autowired
	private GenericCrudBO<TestEntity> genericBOImpl;
	
	@Mock
	private GenericCrudHibernateDAO<TestEntity> genericDAOMock;
	
	private GenericCrudBO<TestEntity> genericBOImplMock = new GenericCrudBOImpl<TestEntity>();

	@Before
	public void before() {
		assertNotNull("No se inicializo el contexto", FrameWorkApplicationContext.getApplicationContext());
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(genericBOImplMock, GENERICDAO, genericDAOMock);
	}

	@Test
	public void createEntity() throws FrameworkPersistenceException {
		TestEntity testEntity = new TestEntity();
		testEntity.setName("AAA");
		genericBOImpl.create(testEntity);
	}
	
	@Test(expected = FrameworkPersistenceException.class)
	public void createEntityException() throws FrameworkPersistenceException {
		genericBOImpl.create(null);
	}	

	@Test
	public void getById() throws FrameworkPersistenceException {
		TestEntity testEntity = genericBOImpl.getById(1L);
		Assert.assertEquals(testEntity.getName(), "BBB");
	}

	@Test(expected = FrameworkPersistenceException.class)
	public void getByIdNull() throws FrameworkPersistenceException {
		genericBOImpl.getById(null);
	}

	@Test
	public void getAll() throws FrameworkPersistenceException {
		List<TestEntity> entities = genericBOImpl.getAll();
		Assert.assertFalse(entities.isEmpty());
	}

	@Test
	public void updateEntity() throws FrameworkPersistenceException {
		TestEntity testEntity = new TestEntity();
		testEntity.setId(2L);
		testEntity.setName("CCC");
		genericBOImpl.update(testEntity);
		testEntity = genericBOImpl.getById(2L);
		Assert.assertEquals(testEntity.getName(), "CCC");
	}
	
	@Test(expected = FrameworkPersistenceException.class)
	public void updateEntityException() throws FrameworkPersistenceException {
		genericBOImpl.update(null);
	}	

	@Test
	public void deleteEntityById() throws FrameworkPersistenceException {
		genericBOImpl.deleteById(3L);
	}

	@Test(expected = FrameworkPersistenceException.class)
	public void deleteEntityNullException() throws FrameworkPersistenceException {
		genericBOImpl.deleteById(null);
	}
	
	@Test(expected = FrameworkPersistenceException.class)
	public void deleteEntityIdNotExistException() throws FrameworkPersistenceException {
		genericBOImpl.deleteById(500L);
	}	
	
	@Test
	public void deleteEntity() throws FrameworkPersistenceException {
		TestEntity testEntity = new TestEntity();
		testEntity.setId(104L);
		genericBOImpl.delete(testEntity);
	}
	
	@Test(expected = FrameworkPersistenceException.class)
	public void deleteEntityException() throws FrameworkPersistenceException {
		genericBOImpl.delete(null);
	}

	@Test(expected = FrameworkPersistenceException.class)
	public void getAllException() throws FrameworkPersistenceException {
		Mockito.doThrow(new FrameworkPersistenceException()).when(genericDAOMock).getAll();
		genericBOImplMock.getAll();
	}
}
