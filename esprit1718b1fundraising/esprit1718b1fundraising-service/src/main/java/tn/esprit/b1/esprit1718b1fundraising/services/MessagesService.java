package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;



/**
 * Session Bean implementation class MessagesEJB
 */
@Stateless
public class MessagesService implements MessagesServiceRemote,MessageServiceLocal {
	
	
	@PersistenceContext
	EntityManager em;
	

	@Override
	public void addMessage(Message message) {
		em.persist(message);
		
	}
	

	@Override
	public Set<Utilisateur> ConversationList(Utilisateur user) {
		TypedQuery<Message> q = em.createQuery("SELECT user FROM Message user where user.receiver.idUser=:id or user.sender.idUser=:idd", Message.class);
		q.setParameter("id", user.getId());
		q.setParameter("idd", user.getId());
		
		List<Message> UserMessages = q.getResultList();
		Set<Utilisateur> contacts= new HashSet<>();
		for( Message m: UserMessages)
		{
			if(m.getSender()!=user)
				contacts.add(m.getReceiver());
			else
				contacts.add(m.getSender());		
		}
		
		return contacts;
	}

	@Override
	public List<Message> getMsgByUserComfirm() {
		TypedQuery<Message> query=em.createQuery("SELECT c FROM Message AS c",Message.class);

		return query.getResultList();
	}

	@Override
	public void save(Message entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Message entity) {

	}


	@Override
	public Message update(Message entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Message find(int entityID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteNouha(int idMsg) {

		Message message=em.find(Message.class, idMsg);
		em.remove(message);
	}

}
