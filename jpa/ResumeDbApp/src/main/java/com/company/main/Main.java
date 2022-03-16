/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

/**
 * @author Elvin
 */
//loosely coupling
//thightly coupling
public class Main {//Java Database Connectivity = JDBC API Specification

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao=Context.instanceUserDao();
        
      for(int i=0;i<10;i++){
           userDao.getById(6);
      }
        
    }

}
