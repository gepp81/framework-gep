package ar.com.gepp.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.gepp.framework.business.bo.crud.GenericCrudBO;
import ar.com.gepp.framework.core.FrameWorkApplicationContext;
import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;
import ar.com.gepp.framework.tools.dozer.mappers.DozerMapper;
import ar.com.gepp.test.dtos.TestDTO;
import ar.com.gepp.test.entities.TestEntity;

/**
 * 
 * @author gpidote
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-root-spring.xml" })
public class DozerMapperTest {

	@Autowired
	private GenericCrudBO<TestEntity> genericBOImpl;

	@Autowired
	@Qualifier(value = "test.dozer")
	private DozerMapper<TestDTO, TestEntity> dozerMapper;

	@Before
	public void before() {
		assertNotNull("No se inicializo el contexto", FrameWorkApplicationContext.getApplicationContext());
	}

	@Test
	public void saveEntity() throws FrameworkPersistenceException {
		TestDTO testDTO = new TestDTO();
		testDTO.setName("burro");
		TestEntity testEntity = dozerMapper.mapToEntity(testDTO);
		genericBOImpl.create(testEntity);
	}

	@Test
	public void getDTO() throws FrameworkPersistenceException {
		TestEntity testEntity = genericBOImpl.getById(1L);
		TestDTO testDTO = dozerMapper.mapToDTO(testEntity);
		Assert.assertNotNull("AGE", testDTO.getAge());
	}

	@Test
	public void saveEntityIdMapping() throws FrameworkPersistenceException {
		TestDTO testDTO = new TestDTO();
		testDTO.setName("burro");
		testDTO.setAge("atril");
		TestEntity testEntity = dozerMapper.mapToEntity(testDTO, "onlyName");
		genericBOImpl.create(testEntity);
	}

	@Test
	public void getDTOIdMapping() throws FrameworkPersistenceException {
		TestEntity testEntity = genericBOImpl.getById(1L);
		TestDTO testDTO = dozerMapper.mapToDTO(testEntity, "onlyName");
		Assert.assertNull(testDTO.getAge());
	}

	/**
	 * Devuelve X cantidades de DTOs
	 * 
	 * @param size {@link int}
	 * @return {@link List}
	 */
	private List<TestDTO> getListDTO(int size) {
		TestDTO testDTO;
		List<TestDTO> list = new ArrayList<TestDTO>(0);
		for (int i = 0; i < size; i++) {
			testDTO = new TestDTO();
			testDTO.setName("SSS");
			testDTO.setAge("ASD");
			list.add(testDTO);
		}
		return list;
	}
	
	/**
	 * Devuelve X cantidades de Entidades
	 * 
	 * @param size {@link int}
	 * @return {@link List}
	 */
	private List<TestEntity> getListEntities(int size) {
		TestEntity test;
		List<TestEntity> list = new ArrayList<TestEntity>(0);
		for (int i = 0; i < size; i++) {
			test = new TestEntity();
			test.setName("SSS");
			test.setAge("ASD");
			list.add(test);
		}
		return list;
	}	

	@Test
	public void getEntitiesFromDTOs() {
		List<TestDTO> dtos = getListDTO(2);
		List<TestEntity> entities = dozerMapper.mapToEntity(dtos);
		Assert.assertNotEquals(entities.size(), 0);
	}
	
	@Test
	public void getEntitiesFromDTOsIdMapping() {
		List<TestDTO> dtos = getListDTO(2);
		List<TestEntity> entities = dozerMapper.mapToEntity(dtos, "onlyName");
		Assert.assertNotEquals(entities.size(), 0);
		Assert.assertNull(entities.get(0).getAge());
	}
	
	@Test
	public void getDTOsFromEntities() {
		List<TestEntity> entities = getListEntities(2);
		List<TestDTO> dtos = dozerMapper.mapToDTO(entities);
		Assert.assertNotEquals(dtos.size(), 0);
	}
	
	@Test
	public void getDTOsFromEntitiesIdMapping() {
		List<TestEntity> entities = getListEntities(2);
		List<TestDTO> dtos = dozerMapper.mapToDTO(entities, "onlyName");
		Assert.assertNotEquals(dtos.size(), 0);
		Assert.assertNull(dtos.get(0).getAge());
	}	

}
