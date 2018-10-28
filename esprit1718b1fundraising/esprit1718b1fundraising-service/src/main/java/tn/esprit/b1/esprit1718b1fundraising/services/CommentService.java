package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.Article;
import tn.esprit.b1.esprit1718b1fundraising.entities.Comment;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateful
@LocalBean
public class CommentService extends GenericDAO<Comment> implements CommentServiceRemote, CommentServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
     * Default constructor. 
     */
    public CommentService() {
    	super(Comment.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	public void save(Comment entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void delete(Comment entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Comment update(Comment entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Comment find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	protected Comment findOneResult(String namedQuery, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return super.findOneResult(namedQuery, parameters);
	}

	@Override
	public List<Comment> getCommentsListByArticleId(int idArticle) {
TypedQuery<Comment> query=entityManager.createQuery("SELECT c FROM Comment AS c WHERE c.article.idArticle=:idArticle",Comment.class);
		
		return query.setParameter("idArticle", idArticle).getResultList();
	}


	
	
}
