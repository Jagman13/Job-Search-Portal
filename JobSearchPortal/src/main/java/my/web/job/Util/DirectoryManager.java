package my.web.job.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import my.web.job.Pojo.Users;


public class DirectoryManager {
	
public String createResumeJobFolder(String FolderName ,Users u ) throws IOException {

	File resumeFolder = new File("C:\\JobSearchPortal\\Resume\\" +FolderName + "\\" + u.getUsername());
	resumeFolder.mkdirs();
	return resumeFolder.getAbsolutePath();
	}
	
public String createCoverJobFolder(String FolderName,Users u ) throws IOException {
	
	File coverFolder = new File("C:\\JobSearchPortal\\CoverLetters\\" +FolderName + "\\" + u.getUsername());
	coverFolder.mkdirs();
	return coverFolder.getAbsolutePath();
}
	
private String generateFileName(MultipartFile multiPartFile) {
		
		return new Date().getTime()+"-" + multiPartFile.getOriginalFilename().replace(" ","_");
		
		
	}

}
