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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 006104
 */
public class ViewItems extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doProcess(req,resp); 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Items> list = new ArrayList<Items>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM app.items where is_deleted=false");
                 while(rs.next()){
                     Items item = new Items();
                     item.setId(rs.getInt("id"));
                     item.setName(rs.getString("name"));
                    try{
                     item.setB(ImageIO.read(rs.getBinaryStream("file")));
                    }
                    catch(IllegalArgumentException ex){
//                        item.set(b);
                    }
                     item.setTs(rs.getTimestamp("created_time"));
                     item.setEditTs(rs.getTimestamp("updated_time"));
//                     item.setBool(rs.getBoolean("Avail"));
                     list.add(item);
                     
                 }
            req.getSession().setAttribute("list", list);
            req.getRequestDispatcher("./viewitems.jsp").forward(req, resp);
        } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();

            Logger.getLogger(ViewItems.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (SQLException ex) {
                        ex.printStackTrace();

            Logger.getLogger(ViewItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                       ex.printStackTrace();

            Logger.getLogger(ViewItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            ex.printStackTrace();
            Logger.getLogger(ViewItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
