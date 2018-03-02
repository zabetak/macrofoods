package org.macrofoods.backend.entities.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The persistent class for the "DATA_SRC" database table.
 * 
 */
@Entity
public class Datasrc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 6)
	private String id;

	@Column(length = 255)
	private String authors;

	@Column
	private Integer end_Page;

	@Column(length = 5)
	private String issue_State;

	@Column(length = 135)
	private String journal;

	@Column
	private Integer start_Page;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(length = 16)
	private String vol_City;

	@Column
	private Integer year;

	public Datasrc() {
	}

	public String getDataSrc_ID() {
		return this.id;
	}

	public void setDataSrc_ID(String dataSrc_ID) {
		this.id = dataSrc_ID;
	}

	public String getAuthors() {
		return this.authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Integer getEnd_Page() {
		return this.end_Page;
	}

	public void setEnd_Page(Integer end_Page) {
		this.end_Page = end_Page;
	}

	public String getIssue_State() {
		return this.issue_State;
	}

	public void setIssue_State(String issue_State) {
		this.issue_State = issue_State;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public Integer getStart_Page() {
		return this.start_Page;
	}

	public void setStart_Page(Integer start_Page) {
		this.start_Page = start_Page;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVol_City() {
		return this.vol_City;
	}

	public void setVol_City(String vol_City) {
		this.vol_City = vol_City;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}