package tn.esprit.b1.esprit1718b1fundraising.mBeans;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.flow.builder.ReturnBuilder;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.memorynotfound.geoip.LookUpProgram;
import com.memorynotfound.geoip.ServerLocation;

import tn.esprit.b1.esprit1718b1fundraising.entities.Article;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.ArticleService;

@ManagedBean(name="articleBean")
@SessionScoped
public class ArticleBean{

	private int idArticle;
	private String body;
	private String name;
	private Boolean favorite;
	private String image;
	
	private Date date;
	private String fontColor;
	private String fontSize;
	private String fontWeight;
	private String articleImage;
	private String fontStyle;
	private String orderedList;
	private String desorderedList;
	private String country;
	private Utilisateur utilisateur;
	
	private Article articleMain;
	private UploadedFile file;
	private CharityProject charityProject;
	private List<Article> filterArticle;
	private List<Article> articles;
	private List<String> names;
	private String filter;
	@EJB
	ArticleService articleService;
	
	@PostConstruct
	public void init() {
		filter="articles";
		articleMain=new Article();
		names = new ArrayList<String>();
		names.add("Culture");
		names.add("Hunger");
		names.add("HumanRights");
		names.add("Education");
		names.add("Technology");
		names.add("Health");
		names.add("Animals");
		names.add("Environment");
		
		articles=articleService.findAll();
		articles.sort(Comparator.comparing(Article::getCountry));
	}
	
	@ManagedProperty(value="#{userBean}")
	UserBean userBean;
	
	
	
	
	
	
	public UserBean getUserBean() {
		return userBean;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
	
	public void filterMe(){
		
		
	}
	 public void upload(FileUploadEvent event) {
		 UploadedFile uploadedFile = event.getFile();
		   articleImage = uploadedFile.getFileName();
		    String contentType = uploadedFile.getContentType();

	        // Now you can save bytes in DB (and also content type?)
	        FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", articleImage, contentType)));
	        

	    }

	public void saveArticle() throws UnknownHostException{
		LookUpProgram obj = new LookUpProgram();
		ServerLocation location = obj.getLocation(InetAddress.getLocalHost().getHostAddress());
		
		 
	             
		articleService.save(new Article(body,name,fontColor,fontSize,fontWeight,articleImage,fontStyle,desorderedList,location.getCountryName(),userBean.getUser()));
	}
	
	
    public String limitBody(String body){
		if(body.length()<90)
			return body;
		return body.substring(0, 90);
	}
	public List<Article> getArticles() {
		
		List<Article> list=articleService.findAll();
		list.sort(Comparator.comparing(Article::getCountry));
		return list;
		
		
		}
	
	public List<Article> haveArticles(String filter) {
		List<Article> list;
		
		if(filter.equals("Culture")||filter.equals("Hunger")||filter.equals("HumanRights")||filter.equals("Education")||filter.equals("Technology")||filter.equals("Health")||filter.equals("Animals")||filter.equals("Environment")){
			 list=articleService.getArticleListByName(filter);
			list.sort(Comparator.comparing(Article::getCountry));
			
			
		}
		else {
		 list=articleService.findAll();
		list.sort(Comparator.comparing(Article::getCountry));
		
		
		
		}
		return list;
		
}
	
		
	

	public String currentArticle(Article article){
		
				this.setBody(article.getBody());
		this.setName(article.getName());
		this.setFontWeight(article.getFontWeight());
		this.setFontStyle(article.getFontStyle());
		this.setDesorderedList(article.getDesorderedList());
		this.setUtilisateur(article.getUtilisateur());
		this.setIdArticle(article.getIdArticle());
		this.setFontColor(article.getFontColor());
		this.setFontSize(article.getFontSize());
		this.setCountry(article.getCountry());
		
		
		articleMain.setBody(article.getBody());
		articleMain.setName(article.getName());
		articleMain.setFontWeight(article.getFontWeight());
		articleMain.setFontStyle(article.getFontStyle());
		articleMain.setDesorderedList(article.getDesorderedList());
		articleMain.setUtilisateur(article.getUtilisateur());
		articleMain.setIdArticle(article.getIdArticle());
		articleMain.setFontColor(article.getFontColor());
		articleMain.setFontSize(article.getFontSize());
		articleMain.setCountry(article.getCountry());
		
		return "/charity/Forum?faces-redirect=true";
	}
	
	
	public String testBody(){
		String c="";
		if(desorderedList==null ||desorderedList.equals("")){
			c=body;
			
			}
			else if(desorderedList.equals("noOrder")) {
				for(int i=0;i<body.length()-1;i++){
					
					
					if(body.charAt(i+1)=='•'){
						c+="<br/>";
					
					}
						c+=body.charAt(i)+"";
				}
				
			}
		 return c;
	}
	
	
	
	

	public List<Article> getFilterArticle() {
		return filterArticle;
	}


	public void setFilterArticle(List<Article> filterArticle) {
		this.filterArticle = filterArticle;
	}


	public List<String> getNames() {
		return names;
	}


	public void setNames(List<String> names) {
		this.names = names;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public CharityProject getCharityProject() {
		return charityProject;
	}

	public void setCharityProject(CharityProject charityProject) {
		this.charityProject = charityProject;
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


	public String getFontStyle() {
		return fontStyle;
	}


	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
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


	public Article getArticleMain() {
		return articleMain;
	}


	public void setArticleMain(Article articleMain) {
		this.articleMain = articleMain;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}



	
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }


	public String getFilter() {
		return filter;
	}


	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	
}
