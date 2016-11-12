/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.item;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;

/**
 *
 * @author 006104
 */
public class Items {
    int id;
    String name;
    BufferedImage b;
    Timestamp ts,editTs;
   

    public int getId() {
        return id;
    }

    public Timestamp getEditTs() {
        return editTs;
    }

    public void setEditTs(Timestamp editTs) {
        this.editTs = editTs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getB() {
        return b;
    }

    public void setB(BufferedImage b) {
        this.b = b;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

   
    
}
