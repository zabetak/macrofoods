package org.macrofoods.backend.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * 
 */
@Entity
@IdClass(DatasrclnID.class)
public class Datasrcln implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Food food;
	@Id
	@ManyToOne
	private Nutrient nutrient;
	@Id
	@ManyToOne
	private Datasrc datasrc;

	public Datasrcln() {
	}

}