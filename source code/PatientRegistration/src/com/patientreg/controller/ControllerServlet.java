package com.patientreg.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.patientreg.modal.Database;
import com.patientreg.modal.Validator;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);


		// process only if its multipart content
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				
				String fname = multiparts.get(0).getString();
				String mname = multiparts.get(1).getString();
				String lname = multiparts.get(2).getString();
				String dob = multiparts.get(3).getString();
				String gender = multiparts.get(4).getString();
				String address = multiparts.get(5).getString();
				String phone = multiparts.get(6).getString();
				String specs = multiparts.get(7).getString();
				java.util.Date dobDate = null;
				String age = "0";
				String error = "no";
				
				
				// validating date and getting age from it. but not validating age yet.
			    try {
			      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			      dobDate = formatter.parse(dob);
			      if(dobDate!=null){
				    	java.util.Date now = new java.util.Date();
				    	long timeBetween = now.getTime() - dobDate.getTime();
				    	double yearsBetween = timeBetween / 3.156e+10;
				    	age = ((int) Math.floor(yearsBetween))+"";
				   }else{
					   error = "Not valid date of birth.";
				   }
			    } catch (ParseException e) {
			    	error = "Not valid date of birth.";
			    }
			    
			   
			   
			    //validations and saving of data start here
			    
			    if(error.equals("no")){
					error = Validator.validateData(fname, mname, lname, age, gender, address, phone);
					if(error.equals("no")){
						//get image
						FileItem imageFile = multiparts.get(8);
						String imagesPath = (String) getServletContext().getAttribute("imagesPath");
						
						error = Validator.validateImage(imageFile, imagesPath );
						if(error.equals("no")){
							String dbURL = (String) getServletContext().getAttribute("dbURL");
							String id = Database.saveRecord(dbURL, fname, mname, lname, dob, age, gender, address, phone, specs);
							if(id.equals("no")){
								request.setAttribute("error", "Database error: unable to save data");
						    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
								dispatcher.forward(request, response);
							}else{
								boolean imageSaved = Database.saveImage(imageFile, id, imagesPath);
								if(imageSaved){
									
									HttpSession session = request.getSession();
									session.setAttribute("message", "Registration is done successfully. Thank You.");
							    response.sendRedirect("DisplayScreen");
								
								}else{
									request.setAttribute("error", "Database error: unable to save image");
							    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
									dispatcher.forward(request, response);
								}
							}
							
						}else{
							request.setAttribute("error", error);
					    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
							dispatcher.forward(request, response);
						}
					}else{
						request.setAttribute("error", error);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
						dispatcher.forward(request, response);
					}
					
			    }else{
			    	request.setAttribute("error", error);
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);	
			    }
			    
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

}
