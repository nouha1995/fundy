package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FounderFields
 *
 */
@Entity

public class FounderFields implements Serializable {

	@EmbeddedId
	private FounderFieldsID founderFieldId;
	@ManyToOne
	@JoinColumn(name="idFieldsPK",insertable=false,updatable=false)
	private Fields field;
	@ManyToOne
	@JoinColumn(name="idFounderPK",insertable=false,updatable=false)
	private Founder founder;
	
	private static final long serialVersionUID = 1L;

	public FounderFields() {
		super();
	}


	public Fields getField() {
		return field;
	}


	public void setField(Fields field) {
		this.field = field;
	}




	public FounderFieldsID getFounderFieldId() {
		return founderFieldId;
	}

	public void setFounderFieldId(FounderFieldsID founderFieldId) {
		this.founderFieldId = founderFieldId;
	}


	public Founder getFounder() {
		return founder;
	}


	public void setFounder(Founder founder) {
		this.founder = founder;
	}


	public FounderFields(FounderFieldsID founderFieldId, Fields field, Founder founder) {
		super();
		this.founderFieldId = founderFieldId;
		this.field = field;
		this.founder = founder;
	}
	
	
   
}
