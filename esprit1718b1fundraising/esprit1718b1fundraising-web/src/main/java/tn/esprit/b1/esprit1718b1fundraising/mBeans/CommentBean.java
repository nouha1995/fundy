package tn.esprit.b1.esprit1718b1fundraising.mBeans;



import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1fundraising.entities.Comment;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.CommentService;

@ManagedBean
@SessionScoped
public class CommentBean {

	private int idComment;
	private String body;
	private Date date;
	private Utilisateur currentUser;
	
	@ManagedProperty(value="#{articleBean}")
	ArticleBean articleBean;

	@ManagedProperty(value="#{userBean}")
	UserBean userBean;
	
	@EJB
	CommentService commentService;
	
	@PostConstruct
	public void init() {
		currentUser=new Utilisateur();
		currentUser=userBean.getUser();
	}
	


	
	public List<Comment> getCommmentsForCurrentArticle(){
		return commentService.getCommentsListByArticleId(articleBean.getIdArticle());
		
	}
	public void saveComment(){
		
		commentService.save(new Comment( body,userBean.getUser(),articleBean.getArticleMain()));
	}
	
	
	public Utilisateur getCurrentUser() {
		return currentUser;
	}




	public void setCurrentUser(Utilisateur currentUser) {
		this.currentUser = currentUser;
	}




	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public ArticleBean getArticleBean() {
		return articleBean;
	}

	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	
	
}
