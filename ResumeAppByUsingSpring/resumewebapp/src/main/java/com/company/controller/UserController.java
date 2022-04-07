package com.company.controller;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceInter userService;

    @RequestMapping(method = RequestMethod.GET, value="/users")
    public String index(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String nationalityIdStr = request.getParameter("nid");
        Integer nationalityId = null;
        if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
            nationalityId = Integer.parseInt(nationalityIdStr);
        }

        List<User> list=userService.getAll(name,surname,nationalityId);

        request.setAttribute("list",list);
        return "users";
    }


}
