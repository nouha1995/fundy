package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment  implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idComment;
	private String body;
	@ManyToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private Article article;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	

	
	public Comment(String body, Utilisateur utilisateur, Article article) {
		super();
		this.body = body;
		this.utilisateur = utilisateur;
		this.article = article;
	}
	public Comment(String body, Utilisateur utilisateur, Article article, Date date) {
		super();
		this.body = body;
		this.utilisateur = utilisateur;
		this.article = article;
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int idComment, String body, Utilisateur utilisateur, Article article, Date date) {
		super();
		this.idComment = idComment;
		this.body = body;
		this.utilisateur = utilisateur;
		this.article = article;
		this.date = date;
	}

	
}
