package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Organization implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String Country;
	
	@Temporal(TemporalType.DATE)
	private Date FoundingYear;
	private String  program;
	private String programMaterials;
	@OneToMany(mappedBy="organization")
	private List<CharityProject> charityProjects=new ArrayList<>();
	private String logo;
	
	
	public Organization() {
		super();
	}
	public Organization(String name, String country, Date foundingYear, String program, String programMaterials) {
		super();
		this.name = name;
		Country = country;
		FoundingYear = foundingYear;
		this.program = program;
		this.programMaterials = programMaterials;
	}
	
	
	
	public Organization(String name, String country, Date foundingYear, String program, String programMaterials,
			String logo) {
		super();
		this.name = name;
		Country = country;
		FoundingYear = foundingYear;
		this.program = program;
		this.programMaterials = programMaterials;
		this.logo = logo;
	}
	public Organization(String name, String country, Date foundingYear, String program, String programMaterials,
			List<CharityProject> charityProjects) {
		super();
		this.name = name;
		Country = country;
		FoundingYear = foundingYear;
		this.program = program;
		this.programMaterials = programMaterials;
		this.charityProjects = charityProjects;
	}
	
	public Organization(int id, String name, String country, Date foundingYear, String program,
			String programMaterials) {
		super();
		this.id = id;
		this.name = name;
		Country = country;
		FoundingYear = foundingYear;
		this.program = program;
		this.programMaterials = programMaterials;
	}
	
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Date getFoundingYear() {
		return FoundingYear;
	}
	public void setFoundingYear(Date foundingYear) {
		FoundingYear = foundingYear;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getProgramMaterials() {
		return programMaterials;
	}
	public void setProgramMaterials(String programMaterials) {
		this.programMaterials = programMaterials;
	}
	public List<CharityProject> getCharityProjects() {
		return charityProjects;
	}
	public void setCharityProjects(List<CharityProject> charityProjects) {
		this.charityProjects = charityProjects;
	}
	
	
}
