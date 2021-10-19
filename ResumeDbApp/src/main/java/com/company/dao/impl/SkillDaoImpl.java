/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin Data Access Object
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("select * from skill");
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addSkill(Skill skill) {
        try (Connection c = connect()) {
          PreparedStatement stmt = c.prepareStatement("insert into skill (name) values(?)");
          stmt.setString(1, skill.getName());
          return stmt.execute();
        }
         catch (Exception ex) {
             ex.printStackTrace();
         }
        return false;
    }

    @Override
    public boolean removeSkill(int id) {
        try (Connection c = connect()) {
            Statement stmt =c.createStatement();
            return stmt.execute("delete from skill where id="+id);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Skill getById(int id) {
        Skill skill;
        try(Connection c=connect()){
            PreparedStatement stmt=c.prepareStatement("select * from skill where id=?");
            stmt.setInt(1, id);
            stmt.execute();
            
            ResultSet rs=stmt.getResultSet();
            
            while(rs.next()){
                int skillId=rs.getInt("id");
                String name=rs.getString("name");
                
                return new Skill(skillId, name);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try(Connection c=connect()){
            PreparedStatement stmt=c.prepareStatement("update skill set name=? where id=?");
            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());
            return stmt.execute();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}

