/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elvin Data Access Object
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthdate = rs.getDate("birthdate");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");

        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        Country nationality = new Country(nationalityId, null, nationalityStr);
        return new User(id, name, surname, phone, email, profileDesc, address, birthdate, nationality, birthplace);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthdate = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");

        User u= new User(id, name, surname, phone, email, profileDesc, address, birthdate, null, null);
        u.setPassword(rs.getString("password"));
        return u;
    }

@Override
    public List<User> getAllUser() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	u.*,"
                    + "	n.nationality, "
                    + "	c.name as birthplace "
                    + "	FROM USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

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
            if (name != null&& !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null&& !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result=null;
        try(Connection c=connect()){
            PreparedStatement stmt=c.prepareStatement("select * from user where email=? and password=?");
        stmt.setString(1,email);
        stmt.setString(2,password);

        ResultSet rs=stmt.executeQuery();
        while (rs.next()){
            result=getUserSimple(rs);
        }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public User findByEmail(String email) {
        User result=null;
        try(Connection c=connect()){
            PreparedStatement stmt=c.prepareStatement("select * from user where email=? ");
            stmt.setString(1,email);

            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                result=getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?,profile_description=?,address=?,birthdate=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	u.*,"
                    + "	n.nationality, "
                    + "	c.name as birthplace "
                    + "	FROM USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + userId);

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private BCrypt.Hasher crypt=BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (name,surname,phone,email,password,profile_description) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, crypt.hashToString(4,u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDesc());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        User u=new User(0,"test","test","test","test",null,null,null,null,null);
       u.setPassword("12345");
        new UserDaoImpl().addUser(u);
    }

}
