package tn.esprit.b1.esprit1718b1fundraising.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1fundraising.entities.CharityProject;
import tn.esprit.b1.esprit1718b1fundraising.entities.Fields;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFields;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFieldsID;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFollowers;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFollowersID;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.utilities.GenericDAO;

@Stateless
@LocalBean
public class UtilisateurService extends GenericDAO<Utilisateur> implements UtilisateurServiceRemote, UtilisateurServiceLocal {
	@PersistenceContext
	 EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UtilisateurService() {
		super(Utilisateur.class);
	}

	
	// login !!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public boolean loginUser(String login, String password) {
		Utilisateur user = findByUsername(login);
		if (user == null) {
			return false;
		} else if (user.getPassword().equals(password)) {
			if (user.isActive() && (!user.isBlocked())) {
				return true;
			}
			return false;

		}
		return false;

	}


		@Override
		public int RedirectUser(Utilisateur user) {

			if (user instanceof Founder) {
				return 1;
			}
			if (user instanceof Investor) {
				return 2;
			}

			return 0;
		}
	//login!!!!!!!!!!!!!!!!!!!!!!
		
		
	@Override
	public Utilisateur findByUsername(String login) {
		try {
			TypedQuery<Utilisateur> q = entityManager.createQuery("SELECT u FROM Utilisateur u where u.login =:login", Utilisateur.class);
			q.setParameter("login", login);
			Utilisateur user = q.getSingleResult();
			return user;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}

	}
	@Override
	public Utilisateur login(String login, String password) {
		Utilisateur utilisateur = null;
		try {
			utilisateur = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:l AND u.password=:p", Utilisateur.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
		} catch (Exception e) {
		}
		return utilisateur;
	}

	@Override
	public void save(Utilisateur entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
	@Override
	public void saveFounder(Founder founder) {
		entityManager.persist(founder);
	}
	@Override
	public void saveUser(Utilisateur entity) {
		// TODO Auto-generated method stub
		super.saveUser(entity);
	}


	@Override
	public void addUtilisateur(Utilisateur user) {
		entityManager.persist(entityManager.merge(user));
	}

	@Override
	public void saveInvestor(Investor investor) {
		entityManager.persist(investor);		
	}
	@Override
	public void addUser(Utilisateur user) {
		entityManager.persist(entityManager.merge(user));
	}
	@Override
	public void delete(Utilisateur entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public void updateUser(Utilisateur user) {
		entityManager.merge(user);

	}
	@Override
	public Utilisateur update(Utilisateur entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Utilisateur find(int entityID) {
		// TODO Auto-generated method stub
		return super.find(entityID);
	}
    @Override
	public int saveNouha(Utilisateur user) {
		entityManager.persist(user);
		return user.getId();
	}
	@Override
	public void affecterChProjectAUserNouha(int chProjectId, int UserId) {
		Utilisateur userEntity=entityManager.find(Utilisateur.class, UserId);
		CharityProject chProjectEntity=entityManager.find(CharityProject.class, chProjectId);
		
		//userEntity.getProjects().add(chProjectEntity);
		chProjectEntity.setUtilisateur(userEntity);
	}
	
	
	@Override
	public boolean verifyMail(String mail) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(mail);
		if (controler.matches()) {
			return true;
		} else {
			return false;
		}

	}
	@Override
	public String codeGeneration() {

		List<Integer> Lascii = new ArrayList<>();
		for (int i = 48; i < 58; i++) {
			Lascii.add(i);
		}

		for (int i = 65; i < 91; i++) {
			Lascii.add(i);
		}

		for (int i = 97; i < 123; i++) {
			Lascii.add(i);
		}
		Random randomizer = new Random();
		String random = "";
		for (int i = 0; i < 8; i++) {
			int x;
			x = Lascii.get(randomizer.nextInt(Lascii.size()));
			random += Character.toString((char) ((char) x));
		}
		return random;
	}
	@Asynchronous
	public void sendMail(String Recipient, String text, String subject) throws AddressException, MessagingException {
		// Recipient's email ID needs to be mentioned.
		String to = Recipient;

		// Sender's email ID needs to be mentioned
		String from = "fundraisingApp";

		// Get system properties
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the default Session object.

		// Session session = Session.getDefaultInstance(props);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("fundraisingPdev@gmail.com", "Fundraising123");
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(text);

			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	@Override
	public boolean checkMailExistance(String email) {
		if (findByEmail(email) == null) {
			return true;
		}
		return false;
	}
	@Override
	public Utilisateur findByEmail(String email) {
		try {
			TypedQuery<Utilisateur> q = entityManager.createQuery("SELECT u FROM Utilisateur u where u.email =:email", Utilisateur.class);
			q.setParameter("email", email);
			Utilisateur user = q.getSingleResult();
			return user;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}
	@Override
	public boolean checkUsernameExistance(String login) {
		if (findByUsername(login) == null) {
			return true;
		}

		return false;
	}



	@Override
	public Fields findFieldsByName(String name) {
		try {
			TypedQuery<Fields> q = entityManager.createQuery("SELECT f FROM Fields f where f.Libelle =:name", Fields.class);
			q.setParameter("name", name);
			Fields fields = q.getSingleResult();
			return fields;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}


	@Override
	public void addFields(Fields field, Utilisateur user) {
		Founder founder = (Founder) user;
		FounderFields Af = new FounderFields();
		Af.setFounder(founder);
		Af.setField(field);
		Af.setFounderFieldId(new FounderFieldsID(field.getIdField(), user.getId()));
		founder.getLfields().add(Af);
		entityManager.persist(Af);
		entityManager.merge(founder);		
	}
	@Override
	public List<Founder> getAllFollowed(Utilisateur user) {
		try {
			TypedQuery<Founder> q = entityManager.createQuery("SELECT a FROM Founder a join a.Followers af where af.user=:Utilisateur",
					Founder.class);
			q.setParameter("Utilisateur", user);
			List<Founder> Lfounder = q.getResultList();
			return Lfounder;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
	@Override
	public void addFollower(Utilisateur follower, Utilisateur user) {
		Founder founder = (Founder) user;
		FounderFollowers Af = new FounderFollowers();
		Af.setFounder(founder);
		Af.setUser(follower);
		Af.setFounderId(new FounderFollowersID(follower.getId(), founder.getId()));
		founder.getFollowers().add(Af);
		entityManager.persist(Af);
		entityManager.merge(founder);

	}
	@Override
	public void removeFollower(Utilisateur follower, Utilisateur user) {
		Founder founder = (Founder) user;
		FounderFollowers Af = new FounderFollowers();
		Af.setFounder(founder);
		Af.setUser(follower);
		Af.setFounderId(new FounderFollowersID(follower.getId(), founder.getId()));
		entityManager.remove(entityManager.merge(Af));

	}
	@Override
	public List<Utilisateur> filterLastNameAndFirstName(String name) {
		try {
			TypedQuery<Utilisateur> q = entityManager
					.createQuery("SELECT u FROM Utilisateur u where u.firstName LIKE :ln or u.lastName LIKE :ln ", Utilisateur.class);
			q.setParameter("ln", '%' + name + '%');
			List<Utilisateur> Luser = q.getResultList();
			return Luser;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}
	@Override
	public void blockUser(Utilisateur user) {

		user.setBlocked(true);
		updateUser(user);
	}

	@Override
	public void unblockUser(Utilisateur user) {
		user.setBlocked(false);
		updateUser(user);
	}
	@Override
	public void enableUser(Utilisateur user) {
		user.setActive(true);
		updateUser(user);

	}

	@Override
	public void disableUser(Utilisateur user) {
		user.setActive(false);
		updateUser(user);
	}
	@Override
	public List<Utilisateur> getAllUsers() {
		TypedQuery<Utilisateur> q = entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class);
		List<Utilisateur> Luser = q.getResultList();
		return Luser;

	}
}
