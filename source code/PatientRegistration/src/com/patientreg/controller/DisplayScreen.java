package com.patientreg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.sql.ResultSet;

import com.patientreg.modal.Database;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/DisplayScreen")
public class DisplayScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayScreen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String dbURL = (String) getServletContext().getAttribute("dbURL");
    ResultSet rs = Database.getAllRecords(dbURL);
    request.setAttribute("rs", rs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("display.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		if(search == null || search.trim().equals("")){
			response.sendRedirect("DisplayScreen");
		}else if(search.length() > 100){
			HttpSession session = request.getSession();
			session.setAttribute("message", "Please enter valid search value. Only Alphanumeric characters are alllowed.");
			response.sendRedirect("DisplayScreen");
		}else if((! (search.matches("[A-Za-z0-9]+")))){
			HttpSession session = request.getSession();
			session.setAttribute("message", "Please enter valid search value. Only Alphanumeric characters are alllowed.");
			response.sendRedirect("DisplayScreen");
		}else{
			String dbURL = (String) getServletContext().getAttribute("dbURL");
		    ResultSet rs = Database.getRecords(dbURL, search);
		    request.setAttribute("rs", rs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("display.jsp");
			dispatcher.forward(request, response);
		}
	}

}
