package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.b1.esprit1718b1fundraising.entities.Comment;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface CommentServiceRemote  extends IGenericDAO<Comment> {
	
	public List<Comment> getCommentsListByArticleId(int idArticle);
}
