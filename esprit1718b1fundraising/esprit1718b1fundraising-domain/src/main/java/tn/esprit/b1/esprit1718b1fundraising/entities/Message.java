package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMessage;
	private String content;
	private String time;
	private int status;
	
	@Lob @Basic(fetch=FetchType.EAGER)
	private byte[] file;
	
	@ManyToOne
	private Utilisateur sender;
	@ManyToOne
	private Utilisateur receiver;
	
	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	} 
	
	
	
	public Message(String content, String time, int status, byte[] file, Utilisateur sender, Utilisateur receiver) {
		super();
		this.content = content;
		this.time = time;
		this.status = status;
		this.file = file;
		this.sender = sender;
		this.receiver = receiver;
	}



	public Message(String content, String time, int status, Utilisateur sender, Utilisateur receiver) {
		super();
		this.content = content;
		this.time = time;
		this.status = status;
		this.sender = sender;
		this.receiver = receiver;
	}



	public Message(String content, String time, int status) {
		super();
		this.content = content;
		this.time = time;
		this.status = status;
	}


	public byte[] getFile() {
		return file;
	}



	public void setFile(byte[] file) {
		this.file = file;
	}



	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public Utilisateur getSender() {
		return sender;
	}
	public void setSender(Utilisateur sender) {
		this.sender = sender;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMessage;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (idMessage != other.idMessage)
			return false;
		return true;
	}
	public Utilisateur getReceiver() {
		return receiver;
	}
	public void setReceiver(Utilisateur receiver) {
		this.receiver = receiver;
	}
	
	
	
   
}
