package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import tn.esprit.b1.esprit1718b1fundraising.entities.Message;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;




@Remote
public interface MessagesServiceRemote {
	
	/**
	 * this method add a message to the database
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * this method return list of conversations
	 * @param message
	 */
	public Set <Utilisateur> ConversationList (Utilisateur u);
	
	public List<Message> getMsgByUserComfirm();
	
	public void deleteNouha(int idMsg);
	

}
