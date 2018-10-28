package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"login","email"})})
@DiscriminatorColumn(name="user_type")
public class Utilisateur implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@NotNull
	private String login;
	@NotNull
	private String password;
	@NotNull
	private String email;
	private boolean isActive;
	private boolean isBlocked;	
	@Lob
	private byte[] picture;
	@OneToMany(mappedBy="utilisateur")
	private List<Project> projects=new ArrayList<>();
	@ManyToMany
	private List<Event> events;
	@OneToMany(mappedBy="user")
	private Set<FounderFollowers> listFollow;
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reclamation> reclamations;

	@OneToMany(mappedBy="utilisateur")
	private List<Comment> comments;
	
	
	@OneToMany(mappedBy="sender",cascade=CascadeType.PERSIST)
	private List<Message> messageSender=new ArrayList<>();
	@OneToMany(mappedBy="receiver",cascade=CascadeType.PERSIST)
	private List<Message> messageReceiver=new ArrayList<>();
	
	private String nouhaImage;
	
	
	
	public String getNouhaImage() {
		return nouhaImage;
	}
	public void setNouhaImage(String nouhaImage) {
		this.nouhaImage = nouhaImage;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked, Date birthdate, List<Project> projects) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
		this.birthdate = birthdate;
		this.projects = projects;
	}

public void addMessageNouha(Message message){
	message.setSender(this);
	this.messageReceiver.add(message);
}
	
	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked, byte[] picture) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.isActive = false;
		this.isBlocked = false;
		this.picture = picture;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public boolean isBlocked() {
		return isBlocked;
	}


	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	
	
	public Utilisateur() {
		super();
	}
	



	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			Date birthdate, List<Project> projects, List<Event> events, List<Reclamation> reclamations,
			List<Message> messageSender, List<Message> messageReceiver) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		this.projects = projects;
		this.events = events;
		
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
	}

	




	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
		
	}


	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			byte[] picture) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.picture = picture;
	}


	public Utilisateur(int id, String firstName, String lastName, String login, String password, String email,
			boolean isActive, boolean isBlocked, byte[] picture, Date birthdate, List<Message> messageSender,
			List<Message> messageReceiver, List<Project> projects, List<Event> events) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
		this.picture = picture;
		this.birthdate = birthdate;
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
		this.projects = projects;
		this.events = events;
	}


	public Utilisateur(String firstName, String lastName, String login, String password, String email, Date birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		
	}



	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Message> getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(List<Message> messageSender) {
		this.messageSender = messageSender;
	}

	public List<Message> getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(List<Message> messageReceiver) {
		this.messageReceiver = messageReceiver;
	}



	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<FounderFollowers> getListFollow() {
		return listFollow;
	}
	public void setListFollow(Set<FounderFollowers> listFollow) {
		this.listFollow = listFollow;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", login=" + login
				+ ", password=" + password + ", email=" + email + ", isActive=" + isActive + ", isBlocked=" + isBlocked
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Utilisateur other = (Utilisateur) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
