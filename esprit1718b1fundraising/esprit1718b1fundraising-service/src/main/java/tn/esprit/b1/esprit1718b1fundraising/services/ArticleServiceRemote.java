package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Article;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface ArticleServiceRemote extends IGenericDAO<Article>{

	List<Article> getArticleListByProjectId(int idProject);
	public List<Article> getArticleListByName(String name) ;
}
