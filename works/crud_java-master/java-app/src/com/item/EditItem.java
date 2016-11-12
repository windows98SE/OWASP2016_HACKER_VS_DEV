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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class EditItem extends HttpServlet{
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
             if(req.getSession().getAttribute("login")==null){
               req.getRequestDispatcher("./login.jsp").forward(req, resp);
               return;
            }
            Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
//            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement ps=con.prepareStatement("update app.items set name=?,file=?,created_time=created_time,updated_time=DEFAULT where id=?");
            ps.setString(1,req.getParameter("name"));
            ps.setBinaryStream(2, fileContent,filePart.getSize());
            ps.setInt(3, Integer.parseInt(req.getParameter("id")));
            ps.executeUpdate();
            req.getRequestDispatcher("./ViewItem").forward(req, resp);
            return;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
