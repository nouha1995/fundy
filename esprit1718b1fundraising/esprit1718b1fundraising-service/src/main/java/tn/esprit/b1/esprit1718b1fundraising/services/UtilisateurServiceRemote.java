package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.List;

import javax.ejb.Remote;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import tn.esprit.b1.esprit1718b1fundraising.entities.*;
import tn.esprit.b1.esprit1718b1fundraising.utilities.IGenericDAO;

@Remote
public interface UtilisateurServiceRemote extends IGenericDAO<Utilisateur> {

	void affecterChProjectAUserNouha(int chProjectId,int UserId);
	int saveNouha(Utilisateur user);

		Utilisateur login(String login, String password);

		boolean loginUser(String login, String password);

		Utilisateur findByUsername(String login);

		int RedirectUser(Utilisateur userLogedIn);
		/**
		 * this method return true in case the user entered a valid mail
		 * @param mail
		 * @return
		 */
		public boolean verifyMail(String mail);
		
		/**
		 * this method returns true in case that the mail entred doesn't exist in the database
		 * @param email
		 * @return
		 */
		public boolean checkMailExistance(String email);
		/**
		 * this method will return the user with the same email given
		 * @param email
		 * @return
		 */
		public Utilisateur findByEmail(String email);
		/**
		 * this method allowed to find an entity of Field by its name
		 * @param name
		 * @return
		 */
		public Fields findFieldsByName(String name);
		/**
		 * this method returns true in case that the login entred doesn't exist in the database
		 * @param login
		 * @return
		 */
		public boolean checkUsernameExistance(String login);

	/**
	 * this method allowed founder to add some fields to his account
	 * @param field
	 * @param founder
	 */
	public void addFields(Fields field , Utilisateur user);
	/**
	 * this method add a user to the database
	 * @param user
	 */
	public void addUtilisateur(Utilisateur user);
	
	/**
	 * this method allowed to Update User's information
	 * @param user
	 */
	
	/**
	 * this method will returns an 8 alphaNumeric code that the user will use it to reset his password
	 * @return
	 */
	public String codeGeneration();
	
	/**
	 * In this method the Recipient will get an email from fannytunisia
	 * @param Recipient
	 * @param text
	 * @param subject
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void sendMail(String Recipient ,String text , String subject) throws AddressException, MessagingException;

	void updateUser(Utilisateur user);
	/**
	 * this method add a user to the database
	 * @param user
	 */
	public void addUser(Utilisateur user);
	void save(Utilisateur entity);

	void saveFounder(Founder founder);
	void saveInvestor(Investor investor);
	
	public void saveUser(Utilisateur entity);
	/**
	 * this method returns all founder followed by user
	 * @param user
	 * @return
	 */
	public List<Founder> getAllFollowed(Utilisateur user);
	/**
	 * this method allowed user to follow another user
	 * @param follower
	 * @param user
	 */
	public void addFollower(Utilisateur follower , Utilisateur user);
	/**
	 * this method allowed user to unfollow another user
	 * @param follower
	 * @param user
	 */
	public void removeFollower(Utilisateur follower , Utilisateur user);
	/**
	 * this method will filter the Users by lastName and FirstName 
	 * @param name
	 * @return
	 */
	public List<Utilisateur> filterLastNameAndFirstName(String name);
	/**
	 * this method allowed to enable a user isEnable will be = true
	 * @param user
	 */
	public void enableUser(Utilisateur user);
	
	/**
	 * this method allowed to disable a user isEnable will be = false
	 * @param user
	 */
	public void disableUser(Utilisateur user);
	
	/**
	 * this method allowed to get all users 
	 * @return
	 */
	public List<Utilisateur> getAllUsers();
	/**
	 * this method allowed you to block a user ; set his isBlock value to true
	 * @param user
	 */
	public void blockUser(Utilisateur user);
	
	/**
	 * this method allowed you to unblock a user ; set his isBlock value to false
	 * @param user
	 */
	public void unblockUser(Utilisateur user);
}
