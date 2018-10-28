package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.Article;
import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
@LocalBean
public class ArticleService extends GenericDAO<Article> implements ArticleServiceRemote, ArticleServiceLocal  {

	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public ArticleService() {
    	super(Article.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	public void save(Article entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void delete(Article entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Article update(Article entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Article find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	protected Article findOneResult(String namedQuery, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return super.findOneResult(namedQuery, parameters);
	}

	@Override
	public List<Article> getArticleListByProjectId(int idProject) {
TypedQuery<Article> query=entityManager.createQuery("SELECT c FROM Article AS c WHERE c.charityProject.idProject=:idProject",Article.class);
		
		return query.setParameter("idProject", idProject).getResultList();

		
	}
	
	@Override
	public List<Article> getArticleListByName(String name) {
TypedQuery<Article> query=entityManager.createQuery("SELECT c FROM Article AS c WHERE c.name=:name",Article.class);
		
		return query.setParameter("name",name).getResultList();

		
	}
}
