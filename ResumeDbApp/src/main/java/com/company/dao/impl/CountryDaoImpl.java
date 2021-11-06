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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");

                list.add(new Country(id, name, nationality));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Country getById(int id) {
        try(Connection con = connect()) {
            Statement stmt = con.createStatement();
            stmt.execute("select * from country where id=" + id);
            
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");
                int countrId=rs.getInt("id");
                return new Country(countrId, name, nationality);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCountry(Country u) {
        try(Connection con = connect()) {
            PreparedStatement stmt = con.prepareStatement("update country set name=?,nationality=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

      @Override
    public boolean removeCountry(int id) {
        try(Connection c=connect()){
           Statement stmt=c.createStatement();
           return stmt.execute("delete from country where id="+id);
        } catch (Exception ex) {
        ex.printStackTrace();
        return false;
        }
    }

}
