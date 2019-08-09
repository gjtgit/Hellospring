package com.gjt.hellosb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gjt.hellosb.entity.User;
import com.gjt.hellosb.exception.BusinessException;
import com.gjt.hellosb.service.UserService;

@Controller
public class UserController {
    
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(path="/users",method=RequestMethod.GET)
    public ModelAndView getUserList() throws BusinessException {
        if (logger.isInfoEnabled())
            logger.info("******** userController >> getUserList Start *************");
        
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.getUserList();
        mv.addObject("userList",userList);
        mv.setViewName("user/userList");
        if (logger.isInfoEnabled())
            logger.info("******** userController >> getUserList end *************");
        
        return mv;
    }
    
    @RequestMapping(path="/users/{id}",method=RequestMethod.GET)
    public ModelAndView getUser(@PathVariable int id) throws BusinessException {
        if (logger.isInfoEnabled())
            logger.info("******** userController >>  getUser start *************");
        
        ModelAndView mv = new ModelAndView();
        User user = userService.getUserById(id);
        mv.addObject("user",user);
        mv.setViewName("user/userInfo");
        if (logger.isInfoEnabled())
            logger.info("******** userController >>  getUser end *************");
        
        return mv;
    }
    
    @RequestMapping(path="/users/add",method=RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        return "user/edit";
    }
    
    @RequestMapping(path="/users/edit/{id}",method=RequestMethod.GET)
    public String editUser(Model model, @PathVariable int id) throws BusinessException {
        model.addAttribute("user",userService.getUserById(id));
        return "user/edit";
    }
    
    @RequestMapping(path="/users",method=RequestMethod.POST)
    public String saveUser(User user) throws BusinessException{
        User userVO = null;
        if(user.getId()!=null) userVO = userService.getUserById(user.getId());
        if(userVO !=null) {
            userService.updateUserBySelective(user);
        }else {
            userService.addUser(user);
        }
        return "redirect:/users";
    }
    
    @RequestMapping(path="/users/delete/{id}",method=RequestMethod.GET)
    public String deleteUser(@PathVariable int id) throws BusinessException {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
