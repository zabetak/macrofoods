package org.macrofoods.backend.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TagDescription {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagdesc_generator")
	@SequenceGenerator(name = "tagdesc_generator", sequenceName = "tagdesc_seq", allocationSize = 10)
	private Integer id;
	@ManyToOne
	private Tag tag;
	@Column(length = 60)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private LangCode langCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LangCode getLangCode() {
		return langCode;
	}

	public void setLangCode(LangCode langCode) {
		this.langCode = langCode;
	}

}
