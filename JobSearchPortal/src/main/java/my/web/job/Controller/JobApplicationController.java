package my.web.job.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import my.web.job.Dao.EmployeeDao;
import my.web.job.Dao.StudentDao;
import my.web.job.Exception.JobDetailsException;
import my.web.job.Exception.JobApplicationException;
import my.web.job.Pojo.Applications;
import my.web.job.Pojo.JobDetails;
import my.web.job.Pojo.Users;
import my.web.job.Util.DirectoryManager;
import my.web.job.Util.DownloadFile;

@Controller
public class JobApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(JobApplicationController.class);

	@RequestMapping(value = "/student/viewJobs", method = RequestMethod.GET)
	public ModelAndView viewAllJobs(HttpServletRequest request, StudentDao studentDao) throws Exception {
		Users users = (Users) request.getSession().getAttribute("userSession");
		try {
			request.getSession().setAttribute("name", users);
			List<JobDetails> jobDetails = studentDao.listAllJobs();
			request.getSession().setAttribute("jobID", jobDetails);
			return new ModelAndView("student-viewJob", "allJobs", jobDetails);
		} catch (JobApplicationException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("student-message", "message", "Error occured while retrieving jobs");
		}

	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView displayEmployerPage(ModelAndView mv) {
		mv.setViewName("student");
		return mv;

	}

	@RequestMapping(value = "/student/applyJob.htm", method = RequestMethod.GET)
	public ModelAndView showApplicationPage(ModelAndView mv, HttpServletRequest request, EmployeeDao empDao,
			StudentDao studentDao) throws JobDetailsException, JobApplicationException {
		logger.info("*************In apply JOb");
		HttpSession session = request.getSession(false);
		if (session != null) {
			Users u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("unauthorizedaccess");
			} else {

				boolean ifAlreadyApplied = true;
				List<JobDetails> details = new ArrayList<JobDetails>();
				Long id = Long.parseLong(request.getParameter("jobID"));

				JobDetails jobdetails = empDao.getJobDetails(id);

				ifAlreadyApplied = studentDao.checkIfAlreadyApplied(u, jobdetails);
				if (ifAlreadyApplied == true) {
					List<JobDetails> jobDetails = studentDao.listAllJobs();
					request.getSession().setAttribute("jobID", jobDetails);
					mv.addObject("error", "You have already applied for this job");
					mv.setViewName("student-viewJob");
				} else {
					JobDetails job = null;
					try {
						job = empDao.getJobDetails(id);
					} catch (JobDetailsException e) {
						return new ModelAndView("student-message", "message",
								"Error while getting jobDetails.Please try after some time !!");
					}

					mv.addObject("job", job);
					mv.addObject("id", id);
					mv.addObject("files", new Applications());
					mv.setViewName("applyJob");

				}
			}
		}
		return mv;
	}

	@RequestMapping(value = "/student/applyJob", method = RequestMethod.POST)
	public ModelAndView applyJob(@ModelAttribute("files") Applications app, ModelAndView mv, HttpServletRequest request,
			EmployeeDao empDao, StudentDao studentDao)
			throws JobApplicationException, JobDetailsException, IllegalStateException, IOException {
		logger.info("*************In apply JOb");
		HttpSession session = request.getSession(false);
		Users u;
		if (session != null) {
			u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("student-message");
			} else {

				Long id = Long.parseLong(request.getParameter("id"));

				JobDetails jobdetails = empDao.getJobDetails(id);

				DirectoryManager m = new DirectoryManager();
				String resumePath = m.createResumeJobFolder(request.getParameter("id"), u);
				String coverPath = m.createCoverJobFolder(request.getParameter("id"), u);

				CommonsMultipartFile resumeUploaded = (CommonsMultipartFile) app.getResume();
				String fileName = resumeUploaded.getOriginalFilename().replace(" ", "_");
				File localFile = new File(resumePath + File.separator + fileName);
				resumeUploaded.transferTo(localFile);
				request.getSession().setAttribute("JobID", jobdetails);

				// Going to set path for Cover Letter
				CommonsMultipartFile coverLetterUploaded = (CommonsMultipartFile) app.getCoverLetter();
				String coverName = coverLetterUploaded.getOriginalFilename().replace(" ", "_");

				try {

					File file1 = new File(coverPath, coverName);
					FileOutputStream fos = new FileOutputStream(file1);
					fos.write(app.getCoverLetter().getBytes());
					fos.close();
					fos.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}
				boolean allowed = DownloadFile.checkExtension(localFile.getPath());
				boolean isAllowed= DownloadFile.checkExtension(coverPath + File.separator + coverName) ;
				if(!allowed || !isAllowed) {
					mv.addObject("message", "Extension not allowed.Allowed extensions .pdf ,.doc ,.docx!!");
					mv.setViewName("student-message");
					return mv;
				}else {
					app.setResumePath(localFile.getPath());
					app.setCoverLetterPath(coverPath + File.separator + coverName);
				System.out.println(app.getResumePath());
				System.out.println("***********************Cover Uploaded at************" + app.getResumePath());
				
				app.setUser(u);
				app.setJobdetails(jobdetails);
				studentDao.saveFiles(app);

			}}

			
		}
		mv.addObject("message", "Sucessfully Applied for job !!");
		mv.setViewName("student-message");
		return mv;

	}

	@RequestMapping(value = "/student/appliedJobs", method = RequestMethod.GET)
	public ModelAndView getAllAppliedJobs(ModelAndView mv, HttpServletRequest request, Applications application,
			EmployeeDao empDao, StudentDao studentDao) throws JobApplicationException, JobDetailsException {

		logger.info("*************In view applied Job");
		HttpSession session = request.getSession(false);
		Users u;
		if (session != null) {
			u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("unauthorizedaccess");
			} else {
				List<Applications> app = studentDao.listAllAppliedJobs(u);
				List<JobDetails> jobs = new ArrayList<JobDetails>();

				for (Applications a : app) {
					long jobId = a.getJobdetails().getJobId();
					jobs.add(empDao.getJobDetails(jobId));
				}

				mv.addObject("jobs", jobs);
				mv.setViewName("student-appliedJobs");

			}
		}
		return mv;
	}

	@RequestMapping(value = "/student/deleteApplication", method = RequestMethod.GET)
	public ModelAndView deleteApplication(ModelAndView mv, HttpServletRequest request, Applications application,
			EmployeeDao empDao, StudentDao studentDao) throws JobApplicationException, JobDetailsException {

		logger.info("In apply Job");
		HttpSession session = request.getSession(false);
		Users u;
		if (session != null) {
			u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("unauthorizedaccess");
			} else {

				Long id = Long.parseLong(request.getParameter("jobId"));
				JobDetails job = empDao.getJobDetails(id);
				boolean isApplied = studentDao.checkIfAlreadyApplied(u, job);
				if (isApplied) {
					studentDao.deleteApplication(job, u);

					mv.addObject("message", "Your Application has been withdrawn");
					mv.setViewName("student-message");
				} else {
					mv.addObject("message", "You are trying to delete a applicantion which doesnt belong to you");
					mv.setViewName("student-message");
				}
			}
		}
		return mv;
	}

	@RequestMapping(value = "/student/downloadPdf", method = RequestMethod.GET)
	public ModelAndView downloadPdf(ModelAndView mv, HttpServletRequest request, Applications application,
			EmployeeDao empDao, StudentDao studentDao) throws JobApplicationException, JobDetailsException {

		logger.info("In PDF downloader");
		List<JobDetails> jobs = new ArrayList<JobDetails>();
		HttpSession session = request.getSession(false);
		Users u;
		if (session != null) {
			u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("unauthorizedaccess");
			} else {
				List<Applications> app = studentDao.listAllAppliedJobs(u);

				for (Applications a : app) {
					long jobId = a.getJobdetails().getJobId();
					jobs.add(empDao.getJobDetails(jobId));
				}

			}
		}
		return new ModelAndView("pdfView", "jobs", jobs);
	}

}