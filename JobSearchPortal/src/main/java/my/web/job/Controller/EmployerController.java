package my.web.job.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.web.job.Dao.EmployeeDao;
import my.web.job.Dao.StudentDao;
import my.web.job.Dao.UserDao;
import my.web.job.Exception.JobApplicationException;
import my.web.job.Exception.JobDetailsException;
import my.web.job.Exception.UserException;
import my.web.job.Pojo.Applications;
import my.web.job.Pojo.JobDetails;
import my.web.job.Pojo.Users;
import my.web.job.Util.DownloadFile;
import my.web.job.Validators.JobDetailsValidator;

@Controller
public class EmployerController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);
	
	@Autowired
	@Qualifier("jobDetailsValidator")
	private JobDetailsValidator validator;

	@InitBinder("job")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/employer/postJob", method=RequestMethod.GET)
	public ModelAndView displayJobPosting(ModelAndView mv ,JobDetails job)
	{	mv.addObject("job", job);
		mv.setViewName("postJob");
		return mv;
		
	}
	
	@RequestMapping(value="/employer", method=RequestMethod.GET)
	public ModelAndView displayEmployerPage(ModelAndView mv ,JobDetails job)
	{	mv.addObject("job", job);
		mv.setViewName("employer");
		return mv;
		
	}
	
	@RequestMapping(value="/employer/postJob", method=RequestMethod.POST)
	public ModelAndView postJob(ModelAndView mv ,@ModelAttribute("job") @Validated JobDetails job , BindingResult result , HttpServletRequest request,EmployeeDao empDao) throws JobDetailsException
	{	
		if (result.hasErrors()) {
			mv.addObject("job", job);
			mv.setViewName("postJob");
			return mv;
		}
		HttpSession session = request.getSession(false);
		if(session != null) {
		Users u = (Users)session.getAttribute("userSession");
		if(u==null) {
			mv.addObject("message","Kindly Login to post a Job");
			mv.setViewName("error");
		}else {
			
			job.setUser(u);
			job = empDao.jobPosting(job);
			if (job != null) {
				mv.addObject("message", "Job has been posted successfully!");
				mv.setViewName("employer");
			} else {
				mv.addObject("message", "Some issue occurred while posting the job.Kindly try again !");
				mv.setViewName("postJob");
			}
			
		}
		}
		return mv;
	}
	
	@RequestMapping(value="/employer/viewPost", method=RequestMethod.GET)
	public ModelAndView viewJobPosting(ModelAndView mv ,HttpServletRequest request,EmployeeDao empDao) throws JobDetailsException
	{	HttpSession session = request.getSession(false);
		if(session != null) {
			Users u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				
				List<JobDetails> list = empDao.viewJobPosting(u);
				mv.addObject("list" ,list);
				mv.setViewName("employer-viewPost");
			}

		}
		return mv ;
	}
	
	@RequestMapping(value="/employer/deleteJob", method=RequestMethod.GET)
	public ModelAndView JobPosting(ModelAndView mv ,HttpServletRequest request,EmployeeDao empDao,StudentDao studentDao,UserDao userDao) throws JobDetailsException, JobApplicationException, UserException
	{	HttpSession session = request.getSession(false);
		Users u ;
		if(session != null) {
			u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("unauthorizedaccess");
			}else {
				
				
				Long id = Long.parseLong(request.getParameter("jobID"));
	
					logger.info("Going to delete the job with Id :" +id);
					boolean isPosted = empDao.checkIfUserPostedJob(u,id);
					if(isPosted) {
					JobDetails job=	empDao.getJobDetails(id);
					studentDao.removeAllApplications(job);
					empDao.deleteJob(id);
					mv.addObject("message", "Post has been deleted");
					mv.setViewName("employer-message");
					
				}else {
					List<JobDetails> list = empDao.viewJobPosting(u);
					mv.addObject("list" ,list);
					mv.addObject("message", "This job is not posted by you !! Kindly delete your post!!");
					mv.setViewName("employer-viewPost");
				}
				}
				
			
		}
		return mv ;
	}
	
	
	
	@RequestMapping(value="/employer/updateJobDetails.htm", method=RequestMethod.GET)
	public ModelAndView showUpdatePage(ModelAndView mv ,HttpServletRequest request,EmployeeDao empDao , JobDetails job) throws JobDetailsException
	{	HttpSession session = request.getSession(false);
		if(session != null) {
			Users u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				Long id = Long.parseLong(request.getParameter("jobID"));
				logger.info("Going to upate the job with Id :" +id);
				boolean isPosted = empDao.checkIfUserPostedJob(u,id);
				if(isPosted) {
				job = empDao.getJobDetails(id);
				mv.addObject("job", job);
				mv.setViewName("updateJobPost");
			}else {
				List<JobDetails> list = empDao.viewJobPosting(u);
				mv.addObject("list" ,list);
				mv.addObject("message", "This job is not posted by you !! So you cant update it");
				mv.setViewName("employer-viewPost");
				}
			}
				

		}
		return mv ;
	}
	
	@RequestMapping(value="/employer/updateJob", method=RequestMethod.POST)
	public ModelAndView UpdateJobPosting(ModelAndView mv ,HttpServletRequest request,EmployeeDao empDao ,@ModelAttribute("job") @Validated JobDetails job ,BindingResult result) throws JobDetailsException
	{	
		if (result.hasErrors()) {
		mv.addObject("job", job);
		mv.setViewName("updateJobPost");
		return mv;
		}
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			Users u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				Long id = Long.parseLong(request.getParameter("id"));
				job.setJobId(id);
				empDao.updateJob(job);
				
				List<JobDetails> list = empDao.viewJobPosting(u);
				mv.addObject("list" ,list);
				mv.addObject("message", "The job has been updated !");
				mv.setViewName("employer-viewPost");
					
				
				
			}

		}
		return mv ;
	}
	
	@RequestMapping(value="/employer/viewApplicants.htm", method=RequestMethod.GET)
	public ModelAndView viewApplicantsApplied(ModelAndView mv ,HttpServletRequest request, UserDao userDao ,EmployeeDao empDao , JobDetails job,StudentDao studentDao) throws  JobApplicationException, UserException, JobDetailsException
	{	HttpSession session = request.getSession(false);
		Users u = null;
		if(session != null) {
			u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				Long id = Long.parseLong(request.getParameter("jobID"));
				boolean ifPosted = empDao.checkIfUserPostedJob(u, id) ;
				if(ifPosted) {
				List<Applications> app = studentDao.listOfApplicants(id);
				if(app==null || app.isEmpty()) {
					List<JobDetails> list = empDao.viewJobPosting(u);
					mv.addObject("list" ,list);
					mv.addObject("message", "No applicants for this job");
					mv.setViewName("employer-viewPost");
				} else {
				List<Users> users = new ArrayList<Users>();
				for(Applications a : app) {
					Long userid = a.getUser().getUserId();							
					users.add(userDao.getUser(userid));
					
					mv.addObject("listapp",app);
					mv.addObject("listuser",users);
					mv.setViewName("applicantsInfo");
				}
				}
					
				}else {
					
					List<JobDetails> list = empDao.viewJobPosting(u);
					mv.addObject("list" ,list);
					mv.addObject("message", "This job is not posted by you !! So you cannot see the applicants address");
					mv.setViewName("employer-viewPost");
				}
				
			}

		}
		return mv ;
	}
	
	
	@RequestMapping(value="/employer/downloadResume.htm", method=RequestMethod.GET)
	public ModelAndView downloadResume(ModelAndView mv ,HttpServletRequest request, DownloadFile file ,UserDao userDao ,EmployeeDao empDao , JobDetails job,StudentDao studentDao) throws  JobApplicationException, UserException
	{	logger.info("In Download Resume Method");
		HttpSession session = request.getSession(false);
		if(session != null) {
			Users u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				String url = (request.getParameter("path"));
				

				String name = (request.getParameter("email"));
				logger.info("Email address is :: " +name);
				boolean isFileDownloaded = file.download(name, "resume", url);
				if(isFileDownloaded)
				{	mv.addObject("message", "FileDownloaded. Kindly check Downloads");
					mv.setViewName("employer-message");
				}else {
					mv.addObject("message", "File could not be downloaded due to some internal issue");
					mv.setViewName("employer-message");
				}
				}
					
				
				
			}

		return mv ;
	}
	
	@RequestMapping(value="/employer/downloadCoverLetter.htm", method=RequestMethod.GET)
	public ModelAndView downloadCoverLetter(ModelAndView mv ,HttpServletRequest request, DownloadFile file ,UserDao userDao ,EmployeeDao empDao , JobDetails job,StudentDao studentDao) throws  JobApplicationException, UserException
	{	logger.info("In DownloadCoverLetterMethod");
		HttpSession session = request.getSession(false);
		if(session != null) {
			Users u = (Users)session.getAttribute("userSession");
			if(u==null) {
				mv.addObject("message","Kindly Login to view Job");
				mv.setViewName("error");
			}else {
				
				String url = (request.getParameter("path"));
				logger.info(" Url for Coverletter" +url);
				String name = (request.getParameter("email"));
				boolean isFileDownloaded = file.download(name, "cover", url);
				if(isFileDownloaded)
				{	mv.addObject("message", "FileDownloaded. Kindly check Downloads");
					mv.setViewName("employer-message");
				}else {
					mv.addObject("message", "File could not be downloaded due to some internal issue");
					mv.setViewName("employer-message");
				}
				}
					
				
				
			}

		return mv ;
	}
}
