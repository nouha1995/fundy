package tn.esprit.b1.esprit1718b1fundraising.mBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.primefaces.context.RequestContext;
import tn.esprit.b1.esprit1718b1fundraising.entities.Admin;
import tn.esprit.b1.esprit1718b1fundraising.entities.Founder;
import tn.esprit.b1.esprit1718b1fundraising.entities.FounderFollowers;
import tn.esprit.b1.esprit1718b1fundraising.entities.Investor;
import tn.esprit.b1.esprit1718b1fundraising.entities.Utilisateur;
import tn.esprit.b1.esprit1718b1fundraising.services.UtilisateurService;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurService userManagment;
	private Utilisateur user;
	private Utilisateur userChoosen ;
	private boolean loggedIN = false;
	List<Utilisateur> Lu;

	@PostConstruct
	public void remplirList() {
		user = new Utilisateur();
		userChoosen = new Utilisateur();
		Lu = new ArrayList<Utilisateur>();
	}
	public UtilisateurService getUserManagment() {
		return userManagment;
	}
	public void setUserManagment(UtilisateurService userManagment) {
		this.userManagment = userManagment;
	}



	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public List<Utilisateur> getLu() {
		return Lu;
	}

	public void setLu(List<Utilisateur> lu) {
		Lu = lu;
	}

	public Utilisateur getUserChoosen() {
		return userChoosen;
	}

	public void setUserChoosen(Utilisateur userChoosen) {
		this.userChoosen = userChoosen;
	}
	
	public String doSignUp() {
		String navTo = "";
		userManagment.addUser(user);
		return navTo;
	}

	/**
	 * this method ensure the login and the redirection
	 * @return
	 */
	public String doLogin() {
		String navTo = "";
		if (userManagment.loginUser(user.getLogin(), user.getPassword())) {
			user = userManagment.findByUsername(user.getLogin());
			if (user instanceof Admin) {
				loggedIN = true;
				navTo = "pages/admin/AdminDashboard?faces-redirect=true";
			} else if (user instanceof Founder){
				loggedIN = true;
				navTo = "pages/users/FounderIndex?faces-redirect=true";
			} else if (user instanceof Investor){
				loggedIN = true;
				navTo = "pages/users/FundInvestorMain?faces-redirect=true";
			}

		} else {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fundraising ERROR", "Wrong username/Password");
	         
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
	        return "";
		}

		// status = "Login Failed please try again";
		return navTo;
	}

	public boolean isLoggedIN() {
		return loggedIN;
	}

	public void setLoggedIN(boolean loggedIN) {
		this.loggedIN = loggedIN;
	}

	/**
	 * this method will returns all the users in the database (in order to display them later)
	 * @return
	 */
	public String ShowAllUsers() {
		String navTo = "ManageUsers?faces-redirect=true";
		Lu = userManagment.getAllUsers();
		for (Utilisateur user : Lu) {
			if (user.getPicture() == null) {

				File file = new File(
						"C:/Users/achraf/Desktop/git/test/esprit1718b1fundraising/esprit1718b1fundraising/esprit1718b1fundraising-client/src/main/java/buttons/PasDePhotoDeProfil.png");
				byte[] bFile = new byte[(int) file.length()];
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
				}
				user.setPicture(bFile);
			}
		}
		return navTo;
	}

	public void validateLoginUnicity(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		String loginToValidate = (String) value;
		if (loginToValidate == null || loginToValidate.trim().isEmpty()) {
			return;
		}
		if (userManagment.findByUsername(loginToValidate)!= null) {
			throw new ValidatorException(new FacesMessage(
					"login already in use!"));
		}
	}
	
	public  void ShowUserProfile(Utilisateur user)
	{
		userChoosen = user;
	}
	
	/**
	 * this method will block the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void BlockUser() throws AddressException, MessagingException
	{
		userManagment.blockUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"Fundraising is sorry to announce that we blocked your account for the moment",
				"Something went wrong");
		Lu = userManagment.getAllUsers();
	}
	
	/**
	 * this method will unblock the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void unBlockUser() throws AddressException, MessagingException
	{
		userManagment.unblockUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"Fundraising is so glad to have you again in our community welcome aboard again",
				"Welcome again");
		Lu = userManagment.getAllUsers();
	}
	
	/**
	 * this method will approve the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void ApproveUser() throws AddressException, MessagingException
	{
		userManagment.enableUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"Fundraising is so glad to have you in our community so please behave and respect other members",
				"Welcome aboard");
		Lu = userManagment.getAllUsers();
	}
	
	public List<Founder> getAllFollowed()
	{
		return userManagment.getAllFollowed(userChoosen);
	}
	
	public int getAllFollow()
	{
		if(userChoosen instanceof Founder)
		{
			Founder founder = (Founder) userChoosen;
			return founder.getFollowers().size();
		}
		return 0;
	}
	
	public List<Utilisateur> getAllFollowers()
	{
		if(userChoosen instanceof Founder)
		{
			List<Utilisateur> Lues = new ArrayList<Utilisateur>();
			Founder founder = (Founder) userChoosen;
			for(FounderFollowers af:founder.getFollowers())
			{
				Lues.add(af.getUser());
			}
			return Lues;
		}
		return null;
	}
	
	

}
