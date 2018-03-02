package org.macrofoods.backend.entities.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the "LANGUAL" database table.
 * 
 */
@Entity
@IdClass(LangualId.class)
public class Langual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Food food;

	@Id
	@ManyToOne
	private Langdesc langdesc;

	public Langual() {
	}

}