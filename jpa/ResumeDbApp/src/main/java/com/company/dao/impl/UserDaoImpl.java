/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Elvin Data Access Object
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "SELECT"
                    + "	u.*,"
                    + "	n.nationality, "
                    + "	c.name as birthplace "
                    + "	FROM USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += "and u.name=?";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += "and  u.surname=?";
            }

            if (nationalityId != null) {
                sql += " and u.nationality_id=?";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
//                User u = getUser(rs);
//                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
//            result=getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? ");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
//                result=getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = em();

        User u = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public User getById(int userId) {
        EntityManager em = em();

        User u = em.find(User.class, userId);
        em.close();
        return u;
    }

    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        EntityManager em = em();
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

}
