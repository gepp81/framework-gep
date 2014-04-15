package ar.com.gepp.framework.tools.dozer.mappers;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

public class HibernateLazyInitializationMapper implements CustomFieldMapper {

	private static final String NO_LAZY = "noLazy";

	/**
	 * 
	 */
	public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping) {

		boolean result = Boolean.FALSE;

		if (sourceFieldValue instanceof HibernateProxy) {
			HibernateProxy hibernateProxy = (HibernateProxy) sourceFieldValue;

			if (isLazyField(fieldMapping, hibernateProxy)) {
				destination = null;
				result = Boolean.TRUE;
			}
		} else if (sourceFieldValue instanceof PersistentCollection) {
			PersistentCollection persistentCollection = (PersistentCollection) sourceFieldValue;

			if (isLazyField(fieldMapping, persistentCollection)) {
				destination = null;
				result = Boolean.TRUE;
			}
		}

		return result;
	}

	/**
	 * Valida si el campo tiene que quedar como Lazy sin recuerar
	 * 
	 * @param fieldMapping
	 * @param persistentField
	 * @return
	 */
	private boolean isLazyField(FieldMap fieldMapping, HibernateProxy hibernateProxy) {
		return !Hibernate.isInitialized(hibernateProxy) && !NO_LAZY.equals(fieldMapping.getCustomConverterParam());
	}

	private boolean isLazyField(FieldMap fieldMapping, PersistentCollection persistentCollection) {
		return !persistentCollection.wasInitialized() && !NO_LAZY.equals(fieldMapping.getCustomConverterParam());
	}

}
