/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 006104
 */
public class EditItemPage extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
             if(req.getSession().getAttribute("login")==null){
               req.getRequestDispatcher("./login.jsp").forward(req, resp);
               return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM app.items WHERE id="+req.getParameter("id"));
            if(rs.next()){
                   req.setAttribute("id",req.getParameter("id"));
                req.setAttribute("name",rs.getString("name"));
            }
            req.getRequestDispatcher("./edititem.jsp").forward(req,resp);
            return;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditItemPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditItemPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
