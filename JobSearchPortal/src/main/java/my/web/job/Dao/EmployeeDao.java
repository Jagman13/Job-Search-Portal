package my.web.job.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.web.job.Controller.HomeController;
import my.web.job.Exception.JobDetailsException;
import my.web.job.Exception.JobApplicationException;
import my.web.job.Pojo.Applications;
import my.web.job.Pojo.JobDetails;
import my.web.job.Pojo.Users;

public class EmployeeDao extends DAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

	public JobDetails jobPosting(JobDetails job) throws JobDetailsException {
		try {
			begin();
			getSession().save(job);
			commit();
			close();
			return job;
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Could not post job " + e);
		}
	}

	public List<JobDetails> viewJobPosting(Users u) throws JobDetailsException {
		try {
			begin();
			Criteria c = getSession().createCriteria(JobDetails.class);
			c.add(Restrictions.eq("user", u));
			List<JobDetails> l = c.list();
			commit();
			
			return l;
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Error while getting list of jobs for User : " + e);
		}
	}

	public JobDetails getJobDetails(Long id) throws JobDetailsException {
		try {
			getSession().clear();
			begin();
			Criteria c = getSession().createCriteria(JobDetails.class);
			c.add(Restrictions.eq("jobId", id));
			c.setMaxResults(1);
			JobDetails job = (JobDetails) c.uniqueResult();
			commit();
			close();
			return job;
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Error while getting job Details of id = " + id + " : " + e);
		}
	}

	public boolean deleteJob(Long id) throws JobDetailsException {
		try {
			begin();
			Query q = getSession().createQuery("delete from JobDetails where jobId = :id");
			q.setLong("id", id);
			q.executeUpdate();
			commit();
			close();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Error while deleting job Details of id = " + id + " : " + e);
		}
	}

	/**
	 * 
	 * @param job
	 * @return
	 * @throws JobDetailsException
	 */
	public JobDetails updateJob(JobDetails job) throws JobDetailsException {
		try {
			logger.info("Going to update job with id : " + job.getJobId());
			begin();
			Query q = getSession().createQuery(
					"update JobDetails set title = :t , companyName = :c ,typeOfJob = :type , country = :country , state = :state , industry = :industry , major = :major , minGpa = :minGpa , jobUrl = :jobUrl , description = :description where jobId = :id");
			q.setString("t", job.getTitle());
			q.setString("c", job.getCompanyName());
			q.setString("type", job.getTypeOfJob());
			q.setString("country", job.getCountry());
			q.setString("state", job.getState());
			q.setString("industry", job.getIndustry());
			q.setString("major", job.getMajor());
			q.setFloat("minGpa", job.getMinGpa());
			q.setString("jobUrl", job.getJobUrl());
			q.setString("description", job.getDescription());
			q.setLong("id", job.getJobId());
			q.executeUpdate();
			commit();
			return job;
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Error while updating job Details of id = " + job.getJobId() + " : " + e);
		}
	}

	public boolean checkIfUserPostedJob(Users u, long id) throws JobDetailsException {

		try {

			begin();
			Criteria c = getSession().createCriteria(JobDetails.class);
			c.add(Restrictions.eq("user", u));
			c.add(Restrictions.eq("jobId", id));
			c.setMaxResults(1);
			JobDetails job = (JobDetails) c.uniqueResult();
			if (job != null) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			rollback();
			throw new JobDetailsException("Error while updating job Details of id = " + id + " : " + e);
		}

	}

}
