/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.item;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author 006104
 */
@MultipartConfig
public class AddItem extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if(req.getSession().getAttribute("login")==null){
               req.getRequestDispatcher("./login.jsp").forward(req, resp);
               return;
        }
        try {
            Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement ps=con.prepareStatement("insert into app.items(id,name,file,is_deleted,created_time,updated_time) values(DEFAULT,?,?,false,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)");
	//		ps.setInt(1,1);
			ps.setString(1,req.getParameter("name"));
              
//                String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
          

//                System.out.println(f.getAbsolutePath());
//                FileInputStream fis = new FileInputStream(f.getAbsolutePath());
		ps.setBinaryStream(2, fileContent,filePart.getSize());
//                 java.util.Date now = Calendar.getInstance().getTime();
//                
//                ps.setTimestamp(3,new Timestamp(now.getTime()) );
//		ps.setString(5,u.getCountry());
		 ps.executeUpdate();
                req.getRequestDispatcher("./ViewItem").forward(req, resp);
                return;
//                    resp.sendRedirect("additem-error.jsp");
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
