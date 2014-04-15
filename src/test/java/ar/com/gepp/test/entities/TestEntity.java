package ar.com.gepp.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.gepp.framework.persistence.entity.Base;

@Entity
public class TestEntity extends Base {

	private static final long serialVersionUID = 4818160769520268434L;

	@Column
	private String name;

	@Column(nullable = true)
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
