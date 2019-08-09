package com.gjt.hellosb.service;

import java.util.List;

import com.gjt.hellosb.entity.User;
import com.gjt.hellosb.exception.BusinessException;

public interface UserService {
    
    public User getUserById(Integer id) throws BusinessException;

    public List<User> getUserList() throws BusinessException;
    
    public boolean deleteUserById(Integer id) throws BusinessException;
    
    public User addUser(User user) throws BusinessException;
    
    public int updateUserBySelective(User user) throws BusinessException;
    
}
