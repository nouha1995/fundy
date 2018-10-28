package tn.esprit.b1.esprit1718b1fundraising.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article  implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idArticle;
	private String body;
	private String name;
	private Boolean favorite;
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String fontColor;
	private String fontSize;
	private String fontWeight;
	private String articleImage;
	private String fontStyle;
	private String orderedList;
	private String desorderedList;
	private String country;
	@ManyToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private CharityProject charityProject;
	
	@OneToMany(mappedBy="article")
	private List<Comment> comments;
	
	
	
	public Article(String name) {
		super();
		this.name = name;
	}
	
	public Article(String body, String name) {
		super();
		this.body = body;
		this.name = name;
	}

	public Article(String body, String name, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.utilisateur = utilisateur;
	}
	

	public Article(String body, String name, String fontWeight, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontWeight = fontWeight;
		this.utilisateur = utilisateur;
	}

	public Article(String body, String name, String fontWeight, String fontStyle, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontWeight = fontWeight;
		this.fontStyle = fontStyle;
		this.utilisateur = utilisateur;
	}
	

	public Article(String body, String name, String fontWeight, String fontStyle, String desorderedList,
			Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontWeight = fontWeight;
		this.fontStyle = fontStyle;
		this.desorderedList = desorderedList;
		this.utilisateur = utilisateur;
	}

	public Article(String body, String name, String fontWeight, String fontStyle, String desorderedList, String country,
			Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontWeight = fontWeight;
		this.fontStyle = fontStyle;
		this.desorderedList = desorderedList;
		this.country = country;
		this.utilisateur = utilisateur;
	}

	
	public Article(String body, String name, String fontWeight, String articleImage, String fontStyle,
			String desorderedList, String country, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontWeight = fontWeight;
		this.articleImage = articleImage;
		this.fontStyle = fontStyle;
		this.desorderedList = desorderedList;
		this.country = country;
		this.utilisateur = utilisateur;
	}

	public Article(String body, String name, Boolean favorite, String image, Date date, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.favorite = favorite;
		this.image = image;
		this.date = date;
		this.utilisateur = utilisateur;
	}
	public Article(String body, String name, String fontColor, String fontWeight, String articleImage, String fontStyle,
			String desorderedList, String country, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontColor = fontColor;
		this.fontWeight = fontWeight;
		this.articleImage = articleImage;
		this.fontStyle = fontStyle;
		this.desorderedList = desorderedList;
		this.country = country;
		this.utilisateur = utilisateur;
	}
	public Article(String body, String name, String fontColor,String fontSize, String fontWeight, String articleImage, String fontStyle,
			String desorderedList, String country, Utilisateur utilisateur) {
		super();
		this.body = body;
		this.name = name;
		this.fontColor = fontColor;
		this.fontSize=fontSize;
		this.fontWeight = fontWeight;
		this.articleImage = articleImage;
		this.fontStyle = fontStyle;
		this.desorderedList = desorderedList;
		this.country = country;
		this.utilisateur = utilisateur;
	}
	public Article(String body, String name, Boolean favorite, String image, Date date, Utilisateur utilisateur,
			CharityProject charityProject) {
		super();
		this.body = body;
		this.name = name;
		this.favorite = favorite;
		this.image = image;
		this.date = date;
		this.utilisateur = utilisateur;
		this.charityProject = charityProject;
	}
	
	
	


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontWeight() {
		return fontWeight;
	}
	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}
	public String getArticleImage() {
		return articleImage;
	}
	public void setArticleImage(String articleImage) {
		this.articleImage = articleImage;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public String getOrderedList() {
		return orderedList;
	}

	public void setOrderedList(String orderedList) {
		this.orderedList = orderedList;
	}

	public String getDesorderedList() {
		return desorderedList;
	}

	public void setDesorderedList(String desorderedList) {
		this.desorderedList = desorderedList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Article(){
		super();
	}
	
	public CharityProject getCharityProject() {
		return charityProject;
	}
	public void setCharityProject(CharityProject charityProject) {
		this.charityProject = charityProject;
	}
	public Article(int idArticle, String body, String name, Boolean favorite, String image, Date date,
			Utilisateur utilisateur) {
		super();
		this.idArticle = idArticle;
		this.body = body;
		this.name = name;
		this.favorite = favorite;
		this.image = image;
		this.date = date;
		this.utilisateur = utilisateur;
	}
	
	
}
