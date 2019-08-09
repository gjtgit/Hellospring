package com.gjt.hellosb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjt.hellosb.dao.UserDao;
import com.gjt.hellosb.dao.UserMapper;
import com.gjt.hellosb.entity.User;
import com.gjt.hellosb.exception.BusinessException;
import com.gjt.hellosb.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    //使用dao方式,不用mapper接口,直接使用mapper.xml中的namespace + sqlId
//    @Autowired
    UserDao userDao;
    
    //使用mapper接口
    @Autowired
    UserMapper userMapper;
    
    //使用cache缓存user,key默认是方法的参数, 如id,也可自定义
//    @Cacheable(cacheNames= {"user"})
    public User getUserById(Integer id) throws BusinessException{
        User user;
        try {
            if (logger.isInfoEnabled()) logger.info(">>>>> getUserById from db ----- ");
//            user = userDao.selectByPrimaryKey(id);
            user = userMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return user;
    }
    
    public List<User> getUserList() throws BusinessException{
        List<User> userList = new ArrayList<User>();
        try {
//            userList = userDao.getAllUsers();
            userList = userMapper.getAllUsers();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return userList;
    }

    public boolean deleteUserById(Integer id) throws BusinessException {
        try {
//            userDao.deleteByPrimaryKey(id);
            userMapper.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public User addUser(User user) throws BusinessException {
        User record = null;
        try {
            user.setUpdatedAt(new Date());
//            userDao.insert(user);
            userMapper.insert(user);
            record = user;
        } catch(Exception e) {
            throw new BusinessException(e);
        }
        return record;
    }
    
    public int updateUserBySelective(User user) throws BusinessException {
        int result = 0;
        try {
            user.setUpdatedAt(new Date());
//            userDao.updateByPrimaryKeySelective(user);
            userMapper.updateByPrimaryKeySelective(user);
            result = 1;
        } catch(Exception e) {
            throw new BusinessException(e);
        }
        return result;
    }

}
