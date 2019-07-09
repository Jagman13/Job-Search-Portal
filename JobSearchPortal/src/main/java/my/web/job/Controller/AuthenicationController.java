package my.web.job.Controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import my.web.job.Dao.UserDao;
import my.web.job.Exception.UserException;
import my.web.job.Pojo.Users;
import my.web.job.Service.EmailService;
import my.web.job.Validators.UsersValidator;

@Controller
public class AuthenicationController {

	@Autowired
	@Qualifier("usersValidator")
	private UsersValidator validator;

	@InitBinder("user")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/*
	 * @Autowired private EmailService emailService;
	 */

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView mv) {
		mv.addObject("user", new Users());
		mv.setViewName("register");
		return mv;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView mv, @ModelAttribute("user") @Validated Users users,BindingResult result, UserDao userDao, HttpServletRequest request) throws UserException, IOException, EmailException {
		if (result.hasErrors()) {
			mv.addObject("user", users);
			mv.setViewName("register");
			return mv;
		}
		Users existingUser;
		System.out.println("--------------******************************----------------" + users.getUsername());
		existingUser = userDao.findByUserName(users.getUsername());
		if (existingUser != null) {
			mv.addObject("error", "This email is already registered!!");
			mv.setViewName("register");
		} else {
			//users.setIsAllowedAccess("N");
			String role = request.getParameter("role");
			users = userDao.register(users, role);

			/*
			 * SimpleMailMessage mailMessage = new SimpleMailMessage();
			 * mailMessage.setTo(users.getUsername());
			 * mailMessage.setSubject("Registration Complete !");
			 * mailMessage.setFrom("jagmangrewal9@gmail.com");
			 * mailMessage.setText("You have been registered to the Job Portal ");
			 * 
			 * emailService.sendEmail(mailMessage);
			 */
			SendEmail(users);
			
			System.out.println("************Done with Controller***************");

			mv.addObject("emailId", users.getUsername());
			mv.setViewName("home");
		}
		return mv;
	}
	  
	 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(ModelAndView mv) {
		mv.addObject("user", new Users());
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getUser(@ModelAttribute("user") Users user, ModelAndView mv, HttpServletRequest request,
			UserDao userDao, BindingResult result) {
		HttpSession session = (HttpSession) request.getSession();
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		Users existingUser;
		try {

			existingUser = userDao.getUser(email, password);
			if (existingUser != null) {
				//if(existingUser.getIsAllowedAccess().equalsIgnoreCase("Y"))
				 {
				if (existingUser.getUserRole().getRole().equalsIgnoreCase("ROLE_STUDENT")) {
					mv.addObject("user", existingUser);
					mv.setViewName("student");
					mv.addObject("role", "student");
					session.setAttribute("userSession", existingUser);

				} else if (existingUser.getUserRole().getRole().equalsIgnoreCase("ROLE_EMPLOYEE")) {
					mv.addObject("user", existingUser);
					mv.setViewName("employer");
					mv.addObject("role", "employer");
					session.setAttribute("userSession", existingUser);

				}
				}
				/*
				 * else { mv.addObject("error","Kindly validate your email address");
				 * mv.setViewName("login");
				 * 
				 * 
				 * 
				 * }
				 */}else {
				mv.addObject("error", "Incorrect credentials !! Kindly Login with correct credentials");
				mv.setViewName("login");

			}
		} catch (UserException e) {

			e.printStackTrace();
			mv.addObject("message", "Sorry for the inconvience. There is some issue !!");
			mv.setViewName("message");

		}
		return mv;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Users u = (Users) session.getAttribute("userSession");
			if (u == null) {
				mv.addObject("message", "Kindly Login to post a Job");
				mv.setViewName("/home");
			} else {
				request.getSession(false).invalidate();
				mv.setViewName("/home");
			}

		}
		return mv;

	}
	
	private void SendEmail(Users u) throws EmailException{
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("jagmangrewal9", "Waheguru!13"));
        email.setSSLOnConnect(true);
        email.setFrom("jagmangrewal9@gmail.com");
        email.setSubject("Registration complete");
        email.setMsg("You have been registered to the Job Portal");
        email.addTo(u.getUsername());
        email.send();    
    }
}
