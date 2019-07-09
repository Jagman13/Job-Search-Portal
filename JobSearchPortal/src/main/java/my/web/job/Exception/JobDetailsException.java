package my.web.job.Exception;

public class JobDetailsException extends Exception {
	
	public JobDetailsException(String message)
	{
		super("JobDetailsException - "+message);
	}
	
	public JobDetailsException(String message, Throwable cause)
	{
		super("JobDetailsException - "+message,cause);
	}

}
