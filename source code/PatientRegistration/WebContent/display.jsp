<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.ResultSet" %>
    
<%
  ResultSet rs = (ResultSet) request.getAttribute("rs");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Page</title>
<style>
	table td, table th {
		padding: 1px;
		border: 1px solid;
		margin: 0;
	}
</style>
</head>
<body id="display-page">

    <br><br><br>
    <header>
        <hgroup>
            <h1>List of all Patients</h1>
        </hgroup>
        <aside>
            <form id="search-form" action="DisplayScreen" method="post">
                <input type="text" name="search" required />
                <input type="submit" value="Search" />
            </form>
        </aside>
    </header>
    <section>
      <br />
      <hr>
      
      <%
      	String message = (String) session.getAttribute("message");
	  	if(message!=null){
	  	    out.write(message);
	  	    out.write("<br><hr>");
	  	    session.removeAttribute("message");
	  	  }
      %>
      <br />
      <table>
        <thead>
          <tr>
          	<th>ID</th>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>DOB</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Phone NO.</th>
            <th>Care Specs</th>
            <th>Image</th>
          </tr>
        </thead>
        <tbody>
          <%
          if(rs!=null){
        	while(rs.next()){
          %>
        		<tr>
        			<td><%= rs.getString("id") %></td>
        			<td><%= rs.getString("fname") %></td>
        			<td><%= rs.getString("mname") %></td>
        			<td><%= rs.getString("lname") %></td>
        			<td><%= rs.getString("dob") %></td>
        			<td><%= rs.getString("age") %></td>
        			<td><%= rs.getString("gender") %></td>
        			<td><%= rs.getString("address") %></td>
        			<td><%= rs.getString("phone") %></td>
        			<td><%= rs.getString("specs") %></td>
        			<td><a href='./images/<%= rs.getString("id") %>.jpg' alt="image-link">open image</a></td>
        		</tr>
          <%
        	}    
          }          
          %>
        </tbody>
      </table>
        
    </section>



    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.validate.min.js" type="text/javascript"></script>
    <script type="text/javascript" >
      $(document).ready(function() {
        $("#search-form").validate();
      });
    </script>
</body>
</html>