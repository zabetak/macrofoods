package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
	@SequenceGenerator(name = "image_generator", sequenceName = "image_seq", allocationSize = 10)
	private Integer id;
	@Column(nullable = false)
	private byte[] data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
