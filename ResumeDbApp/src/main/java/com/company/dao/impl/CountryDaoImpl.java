/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter{

    @Override
    public List<Country> getAll() {
        List<Country> list=new ArrayList<>();
        try {
            Connection c=connect();
            PreparedStatement stmt=c.prepareStatement("select * from country");
            stmt.execute();
            ResultSet rs=stmt.getResultSet();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String nationality=rs.getString("nationality");
                
                list.add(new Country(id, name, nationality));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
}
