package my.web.job.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import my.web.job.Exception.UserException;
import my.web.job.Pojo.UserRole;
import my.web.job.Pojo.Users;



@Component
public class UserDao extends DAO {
	
	public UserDao() {
		super();
	}

	public Users getUser(String email ,String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Users where username = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);
			Users user = (Users) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + email, e);
		}
	}

	public Users register(Users u,String r) throws UserException {
		try {
			begin();
			UserRole role = new UserRole(r);
			Users newUser = new Users(u.getUsername(), u.getPassword(), true, u.getFirstName(),u.getLastName());
			newUser.setUserRole(role);
			role.setUser(newUser);
			u.setEnabled(true);
			getSession().save(newUser);
			getSession().save(role);
			commit();
			close();
			return u;
		}catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void delete(Users user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getFirstName() + "  " + user.getLastName(), e);
		}
	}
	
	public Users findByUserName(String email) throws UserException{
		try {
			
			begin();
			Query q = getSession().createQuery("from Users where username = :e");
			q.setString("e" , email);
			q.setMaxResults(1);
			
			Users u = (Users) q.uniqueResult();
			
			commit();
			return u ; 
			
			
		}catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while getting user with email " +email + " : " + e.getMessage());
		}
		
		
		
		
	}
	
	public List<Users> getPendingUsers() throws UserException{
		try {
			
			begin();
			
			Criteria  cr = getSession().createCriteria(Users.class);
			
			 cr.add(Restrictions.eq("isEnabled" , "N")); 
			 cr.add(Restrictions.eq("role" ,"ROLE_USER"));
			 
			
			List<Users> u =  cr.list();
			commit();
			return u ; 
			
			
		}catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while getting user with Status N" + e.getMessage());
		}
		
		
		
		
	}
	
	public int updateStatus(String emailId) throws UserException{
		try {
			
			begin();
			Query query = getSession().createQuery("update Users set isAllowedAccess = :e" +
    				" where username = :email");
			query.setParameter("e", "Y");
			query.setParameter("email", emailId);
			int result = query.executeUpdate();
			
			commit();
			return result ; 
			
			
		}catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while Updating user Status with email " +emailId + " : " +e.getMessage());
		}
	}
	
	public Users getUser(long id) throws UserException{
		try {
			begin();
			Criteria  cr = getSession().createCriteria(Users.class);
			cr.add(Restrictions.eq("userId" , id));
			cr.setMaxResults(1);
			Users u  = (Users) cr.uniqueResult();
			commit();
			return u;
		}catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while fetching User info for user id -" +id+ " : " +e.getMessage());
        }
	}
}
