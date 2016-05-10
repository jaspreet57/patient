package com.patientreg.modal;

import java.io.File;
import java.nio.file.Files;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.fileupload.FileItem;

public class Validator {
	public static String validateData(String fname, String mname, String lname, String age, String gender, String address, String phone){
		String error = "no";
		
		if(fname.trim().equals("")){
			return "First Name must not be empty.";
		}else if(fname.length() > 100){
			return "First Name must not be more than 100 characters of length.";
		}else if (!fname.matches("^[A-Za-z]{0,100}$")){
			return "First Name must consist of only alphabets.";
		}
		
		if(mname.length() > 100){
			return "Middle Name must not be more than 100 characters of length.";
		}else if (!mname.matches("^[A-Za-z]{0,100}$")){
			return "Middle Name must consist of only alphabets.";
		}
		
		
		if(lname.length() > 100){
			return "Last mname Name must not be more than 100 characters of length.";
		}else if (!lname.matches("^[A-Za-z]{0,100}$")){
			return "Last Name must consist of only alphabets.";
		}
		
		if(Integer.parseInt(age) < 1){
			return "Age must not be less than 1. Check date of birth.";
		}else if (Integer.parseInt(age) > 100){
			return "Age must not be more than 100. Check date of birth.";
		}
		
		if(gender.equals("male") || gender.equals("female")){
			
		}else{
			return "Gender must be male or female.";
		}
		
		if(address.trim().equals("")){
			return "Address must not be empty.";
		}else if(address.length() > 300){
			return "Address must not be more than 300 characters of length.";
		}else if (!address.matches("^[ A-Za-z0-9\n/\\#,;-]*$")){
			return "Address must consist of only alphabets, numeric digits, and special charaters like /,\\,#, ,;,-";
		}
		
		if(phone.trim().equals("")){
			return "Phone must not be empty.";
		}else if(phone.length() != 10){
			return "Phone must contain exactly 10 digits.";
		}else if (!phone.matches("^(?!(\\d)\\1{9})(?!0123456789|1234567890|0987654321|9876543210)\\d{10}$")){
			return "Phone number must have only numeric characters and must not have all zeros or all single numbers or any sequence numbers like 0123456789.";
		}
		
		return error;
	}
	
	
	/*
	 * two parameters : uploaded image file and directory path in which all others images are present.
	 */
	public static String validateImage(FileItem imageFile, String imagesPath){
		String error = "no";
		
		try{
			File folder  = new File(imagesPath);
			File[] allFiles = folder.listFiles();
			if (!imageFile.isFormField()) {
				byte[] uploadImage = imageFile.get();
				if(uploadImage.length == 0){
					error = "Error: Please select image to upload";
				}else if(!(imageFile.getContentType().equals("image/jpeg"))){
					error = "Error: Only jpeg images are accepted. Try another image.";
				}else{
					for(File file: allFiles){
						if(Arrays.equals(uploadImage, Files.readAllBytes(file.toPath()))){
							error = "Error: Image already existing. Please Upload another image.";
							break;
						}
					}
				}
				
				
			}else{
				error = "Error: Uploaded file is not an image file";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			error = "Server Error: Not able to read from existing images";
		}
		
		
		
		return error;
	}
}
