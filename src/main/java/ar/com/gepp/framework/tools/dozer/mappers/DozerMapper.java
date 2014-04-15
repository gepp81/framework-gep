package ar.com.gepp.framework.tools.dozer.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DozerMapper<D, E> {

	private static final Logger log = Logger.getLogger(DozerMapper.class);
	private Class<D> classD;
	private Class<E> classE;
	@Autowired
	@Qualifier("dozerBeanMapper")
	private Mapper mapper;

	/**
	 * Mappea una Entidad a DTO con id de mapping dozer.
	 * 
	 * @param entity
	 * @param idMapping
	 * @return D
	 */
	public D mapToDTO(E entity, final String idMapping) {
		log.infov("DozerMapper - mapToDTO(): Mapeando entity a DTO con id de mapeo ({0})", idMapping);
		return mapper.map(entity, classD, idMapping);
	}

	/**
	 * Mappea a uno a DTO
	 * 
	 * @param something
	 * @return D
	 */
	public D mapToDTO(E entity) {
		log.info("DozerMapper - mapToDTO(): Mapeando entidad a DTO");
		return mapper.map(entity, classD);
	}

	/**
	 * Mappea a uno a Entidad
	 * 
	 * @param something
	 * @return E
	 */
	public E mapToEntity(D dto) {
		log.info("DozerMapper - mapToEntity(): Mapeando DTO a entidad");
		return mapper.map(dto, classE);
	}

	/**
	 * Mappea a uno a Entidad con id mappeo dozer.
	 * 
	 * @param dto
	 * @param idMapping
	 * @return E
	 */
	public E mapToEntity(D dto, final String idMapping) {
		log.infov("DozerMapper - mapToEntity(): Mapeando DTO a entidad con id de mapeo ({0})", idMapping);
		return mapper.map(dto, classE, idMapping);
	}

	/**
	 * Mappea una lista a Entidad
	 * 
	 * @param list
	 * @return List<E>
	 */
	public List<E> mapToEntity(List<D> list) {
		log.info("DozerMapper - mapToEntity(): Mapeando lista de DTO a lista de entidades");
		List<E> listMapped = new ArrayList<E>();
		foldingToEntity(list, listMapped);
		return listMapped;
	}

	/**
	 * Mappea una lista a Entidad con id de mapping dozer.
	 * 
	 * @param list
	 * @param idMapping
	 * @return List<E>
	 */
	public List<E> mapToEntity(List<D> list, final String idMapping) {
		log.infov("DozerMapper - mapToEntity(): Mapeando lista de DTO a lista de entidades con id de mapeo ({0})", idMapping);
		List<E> listMapped = new ArrayList<E>();
		foldingToEntity(list, listMapped, idMapping);
		return listMapped;
	}

	/**
	 * Mappea una lista a DTO
	 * 
	 * @param list
	 * @return List<D>
	 */
	public List<D> mapToDTO(List<E> list) {
		log.info("DozerMapper - mapToDTO(): Mapeando lista de entidades a lista de DTO");
		List<D> listMapped = new ArrayList<D>();
		foldingToDTO(list, listMapped);
		return listMapped;
	}

	/**
	 * Mappea una lista a DTO con un id de mapeo de dozer.
	 * 
	 * @param list
	 * @param idMapping
	 * @return List<D>
	 */
	public List<D> mapToDTO(List<E> list, final String idMapping) {
		log.infov("DozerMapper - mapToDTO(): Mapeando lista de entidades a lista de DTO con id de mapeo ({0})", idMapping);
		List<D> listMapped = new ArrayList<D>();
		foldingToDTO(list, listMapped, idMapping);
		return listMapped;
	}

	/**
	 * Mappea una coleccion de DTO y a una coleccion de Entidades
	 * 
	 * @param objectToFold
	 * @param foldedMapped
	 */
	private void foldingToEntity(Collection<D> objectToFold, Collection<E> foldedMapped) {
		log.infov("DozerMapper - foldingToEntity(): Iterando sobre la coleccion de dto");
		for (D dto : objectToFold) {
			E mappedEntity = mapper.map(dto, classE);
			foldedMapped.add(mappedEntity);
		}
	}

	/**
	 * Mappea una coleccion de Entidades a una coleccion de DTO
	 * 
	 * @param objectToFold
	 * @param foldedMapped
	 */
	private void foldingToDTO(Collection<E> objectToFold, Collection<D> foldedMapped) {
		log.info("DozerMapper - foldingToDTO(): Iterando sobre la coleccion de entidades");
		for (E entity : objectToFold) {
			D mappedDTO = mapper.map(entity, classD);
			foldedMapped.add(mappedDTO);
		}
	}

	/**
	 * Mappea una coleccion de Entidades y a una coleccion de DTO segun un id de mappeo
	 * 
	 * @param objectToFold
	 * @param foldedMapped
	 * @param idMapping
	 */
	private void foldingToDTO(Collection<E> objectToFold, Collection<D> foldedMapped, final String idMapping) {
		log.infov("DozerMapper - foldingToDTO(): Iterando sobre la coleccion de entidades con id de mapeo ({0})", idMapping);
		for (E entity : objectToFold) {
			D mappedDTO = mapper.map(entity, classD, idMapping);
			foldedMapped.add(mappedDTO);
		}
	}

	/**
	 * Mappea una coleccion de DTO y a una coleccion de Entidades segun un id de mappeo
	 * 
	 * @param objectToFold
	 * @param foldedMapped
	 * @param idMapping
	 */
	private void foldingToEntity(Collection<D> objectToFold, Collection<E> foldedMapped, final String idMapping) {
		log.infov("DozerMapper - foldingToEntity(): Iterando sobre la coleccion de dto con id de mapeo ({0})", idMapping);
		for (D dto : objectToFold) {
			E mappedEntity = mapper.map(dto, classE, idMapping);
			foldedMapped.add(mappedEntity);
		}
	}

	public Class<D> getClassD() {
		return classD;
	}

	public void setClassD(Class<D> classD) {
		this.classD = classD;
	}

	public Class<E> getClassE() {
		return classE;
	}

	public void setClassE(Class<E> classE) {
		this.classE = classE;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
