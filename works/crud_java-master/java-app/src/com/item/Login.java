/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.item;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author 006104
 */
public class Login extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement ps = con.prepareStatement("select password from app.users where username='"+username+"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] thedigest = md.digest(bytesOfMessage);
                String pass = (new HexBinaryAdapter()).marshal(thedigest);
                System.out.println(pass);
                System.out.println(rs.getString("password"));
                if(pass.equalsIgnoreCase(rs.getString("password"))){
                    req.getSession().setAttribute("login", 1);
                    req.getRequestDispatcher("./ViewItem").forward(req, resp);
                    return;
                }
                 else{
                    req.getRequestDispatcher("./login.jsp").forward(req, resp);
                    return;
                }
            }
            else{
                req.getRequestDispatcher("./index.jsp").forward(req, resp);                    
                return;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
