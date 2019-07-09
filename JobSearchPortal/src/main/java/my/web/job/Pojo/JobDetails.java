package my.web.job.Pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class JobDetails {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "jobId", unique=true, nullable = false)
		private long jobId;
		
		@ManyToOne(cascade= CascadeType.ALL)
		@JoinColumn(name = "userid")
		private Users user;
		
		@Column
		private String title;
		
		@Column
		private String companyName;
		
		@Column
		private String typeOfJob;
		
		@Column
		private String country;
		
		@Column
		private String state;
		
		@Column
		private String industry;
		
		@Column
		private String major;
		
		@Column 
		private float minGpa ; 
		
		@Column
		private String jobUrl;
		
		@Column
		private String description;
		
		@Temporal(TemporalType.DATE)
		private Date postedOn;
		
		@OneToMany(fetch = FetchType.LAZY)
		private List<Applications> app = new ArrayList<Applications>();
		
		

		public JobDetails() {
			super();
			postedOn = new Date();
		}

		public long getJobId() {
			return jobId;
		}

		public void setJobId(long jobId) {
			this.jobId = jobId;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getTypeOfJob() {
			return typeOfJob;
		}

		public void setTypeOfJob(String typeOfJob) {
			this.typeOfJob = typeOfJob;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getIndustry() {
			return industry;
		}

		public void setIndustry(String industry) {
			this.industry = industry;
		}

		public String getMajor() {
			return major;
		}

		public void setMajor(String major) {
			this.major = major;
		}

		public float getMinGpa() {
			return minGpa;
		}

		public void setMinGpa(float minGpa) {
			this.minGpa = minGpa;
		}

		public String getJobUrl() {
			return jobUrl;
		}

		public void setJobUrl(String jobUrl) {
			this.jobUrl = jobUrl;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getPostedOn() {
			return postedOn;
		}

		public void setPostedOn(Date postedOn) {
			this.postedOn = postedOn;
		}

		public List<Applications> getApp() {
			return app;
		}

		public void setApp(List<Applications> app) {
			this.app = app;
		}
		
		
		
}
	

