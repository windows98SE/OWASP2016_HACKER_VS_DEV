/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.item;

import java.io.IOException;
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
public class Search extends HttpServlet{
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        try {
             if(req.getSession().getAttribute("login")==null){
               req.getRequestDispatcher("./login.jsp").forward(req, resp);
               return;
            }
            req.getRequestDispatcher("./search.jsp").forward(req,resp);
        } catch (ServletException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
