package ar.com.gepp.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.gepp.framework.persistence.entity.Base;

@Entity
public class TestEntity extends Base {

	private static final long serialVersionUID = 4818160769520268434L;

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
