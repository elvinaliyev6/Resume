/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

/**
 * @author Elvin
 */
public interface UserDaoInter {
    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User findByEmailAndPassword(String email,String password);
    public User findByEmail(String email);
    public List<User> getAllUser();

    public User getById(int id);

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);

}
