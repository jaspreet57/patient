package com.patientreg.modal;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;

public class Database {
	public static String saveRecord(String dbURL, String fname, String mname, String lname, String dob, String age, String gender, String address, String phone, String specs){
		String id = "no";
		String[] dobParts = dob.split("/");
		Connection connection = null;
		try{
			while(true){
				id = "99"+dobParts[0]+(int)(Math.random()*100)+dobParts[1]+(int)(Math.random()*100)+dobParts[2]+(int)(Math.random()*100);
				connection = DriverManager.getConnection(dbURL);
	            if (connection != null) {
	                ResultSet rs = null;
	        		try{
	        			PreparedStatement ps = connection.prepareStatement("select fname from patient where id=?");
	        			ps.setString(1, id);
	        			rs = ps.executeQuery();
	        			if(rs!=null && rs.next()){
	        				// do again while loop
	        			}else{
	        				try{
	    	        			PreparedStatement ps1 = connection.prepareStatement("insert into patient (id, fname, mname, lname, dob, age, gender, phone, address, specs) values (?,?,?,?,?,?,?,?,?,?)");
	    	        			ps1.setString(1, id);
	    	        			ps1.setString(2,  fname);
	    	        			ps1.setString(3, mname);
	    	        			ps1.setString(4, lname);
	    	        			ps1.setString(5, dob);
	    	        			ps1.setString(6, age);
	    	        			ps1.setString(7, gender);
	    	        			ps1.setString(8, phone);
	    	        			ps1.setString(9, address);
	    	        			ps1.setString(10, specs);
	    	        			ps1.executeUpdate();
	    	        			break;
	    	        		}catch(SQLException e){
	    	        			e.printStackTrace();
	    	        			id="no";
	    	        			break;
	    	        		}
	        			}		
	        		}catch(SQLException e){
	        			e.printStackTrace();
	        			id="no";
	        			break;
	        		}
	            }else{
	            	id="no";
	            	break;
	            }
			}
			
        }catch(Exception e1){
        	e1.printStackTrace();
        	id="no";
        }finally{
        	if(connection!=null){
        		try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
		
		
		return id;
	}
	
	
	public static ResultSet getAllRecords(String dbURL){
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(dbURL);
			if(connection!=null){
				try{
					PreparedStatement ps = connection.prepareStatement("select * from patient");
					rs = ps.executeQuery();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				rs = null;
			}
		}catch(Exception e1){
			rs = null;
			e1.printStackTrace();
		}
		
		return rs;
	}
	
	public static ResultSet getRecords(String dbURL, String search){
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(dbURL);
			if(connection!=null){
				try{
					PreparedStatement ps = connection.prepareStatement("select * from patient where id = ? or fname = ? or mname=? or lname = ? or dob like ? or age = ? or gender = ? or phone = ? or specs = ? or address like ?");
					ps.setString(1, search);
					ps.setString(2, search);
					ps.setString(3, search);
					ps.setString(4, search);
					ps.setString(5, "%"+search+"%");
					ps.setString(6, search);
					ps.setString(7, search);
					ps.setString(8, search);
					ps.setString(9, search);
					ps.setString(10, "%"+search+"%");
					rs = ps.executeQuery();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				rs = null;
			}
		}catch(Exception e1){
			rs = null;
			e1.printStackTrace();
		}
		
		return rs;
	}
	
	
	
	public static boolean saveImage(FileItem image, String id, String imagesPath){
		boolean success = true;
		try {
			image.write(new File(imagesPath+ "/" + id +".jpg"));
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}
}
