package my.web.job.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.web.job.Controller.HomeController;
import my.web.job.Exception.JobApplicationException;
import my.web.job.Pojo.Applications;
import my.web.job.Pojo.JobDetails;
import my.web.job.Pojo.Users;

public class StudentDao extends DAO {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * 
	 * @return
	 * @throws JobApplicationException
	 */
	public List<JobDetails> listAllJobs() throws JobApplicationException {
		try {
			begin();
			Query q = getSession().createQuery("from JobDetails");

			List<JobDetails> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new JobApplicationException("Error while getting jobs" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param user
	 * @param jobdetails
	 * @return
	 * @throws JobApplicationException
	 */

	public boolean checkIfAlreadyApplied(Users user, JobDetails jobdetails) throws JobApplicationException {
		try {
			boolean result = false;
			logger.info(
					"Going to chekck if user has already applied" + jobdetails.getJobId() + "-----" + user.getUserId());
			begin();
			Criteria criteria = getSession().createCriteria(Applications.class);
			criteria.add(Restrictions.eq("jobdetails", jobdetails));
			criteria.add(Restrictions.eq("user", user));
			List<Applications> results = criteria.list();
			logger.info("list of results" + results);
			commit();
			if (results.isEmpty() || results == null) {
				result = false;
			} else {
				result = true;
			}
			logger.info("Has User applied for this job ? " + result);
			return result;

		} catch (HibernateException e) {
			rollback();
			throw new JobApplicationException("Error while fetching job for Userid - " + user.getUserId() + " : " + e);
		}
	}

	public void saveFiles(Applications application) throws JobApplicationException {
		try {

			begin();
			getSession().save(application);
			commit();
		} catch (HibernateException e) {
			rollback();

			throw new JobApplicationException("Error while saving Job Application : " + e);
		}
	}

	public List<Applications> listAllAppliedJobs(Users user) throws JobApplicationException {
		try {

			begin();
			Criteria c = getSession().createCriteria(Applications.class);
			c.add(Restrictions.eq("user", user));
			List<Applications> list = c.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();

			throw new JobApplicationException(
					"Error while fetching Job Application details for User - " + user.getUserId() + " : " + e);
		}
	}

	public void deleteApplication(JobDetails job, Users user) throws JobApplicationException {
		try {

			begin();
			Query q = getSession().createQuery("delete from Applications where jobdetails = :job AND user = :user");
			q.setParameter("job", job);
			q.setParameter("user", user);
			q.executeUpdate();
			commit();
			close();
		} catch (HibernateException e) {
			rollback();

			throw new JobApplicationException("Error while deleting Job Application : " + e);
		}
	}

	/**
	 * Method to get the List of applicants for particular job
	 * 
	 * @param jobid
	 * @return
	 * @throws JobApplicationException
	 */
	public List<Applications> listOfApplicants(long jobId) throws JobApplicationException {
		try {
			begin();
			logger.info("Going to fetch the list of applicants for job id :" + jobId);
			Query query = getSession().createQuery("from Applications where jobdetails= '" + jobId + "' ");
			List<Applications> list = query.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new JobApplicationException(
					"Could not fetch the list for applicants for job Id :" + jobId + " : " + e.getMessage());
		}
	}
	
	/**
	 * Method to get the List of applicants for particular job
	 * 
	 * @param jobid
	 * @return
	 * @throws JobApplicationException
	 */
	public int removeAllApplications(JobDetails job) throws JobApplicationException {
		try {
			begin();
			logger.info("Going to delete the list of applicants for job id :" + job.getJobId());
			Query query = getSession().createQuery("delete from Applications where jobdetails= '" + job.getJobId() + "' ");
			int i =query.executeUpdate();
			commit();
			close();
			return i;
		} catch (HibernateException e) {
			rollback();
			throw new JobApplicationException(
					"Could not fetch the list for applicants for job Id :" + job.getJobId() + " : " + e.getMessage());
		}
	}

}
