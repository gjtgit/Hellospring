package com.gjt.hellosb.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gjt.hellosb.entity.User;
import com.gjt.hellosb.exception.DAOException;

@Repository
public class UserDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    public User selectByPrimaryKey(Integer id) throws DAOException {
        try {
            return sqlSessionTemplate.selectOne("com.gjt.hellosb.dao.UserMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
    public List<User> getAllUsers() throws DAOException {
        try {
            return sqlSessionTemplate.selectList("com.gjt.hellosb.dao.UserMapper.getAllUsers");
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
    public int deleteByPrimaryKey(Integer id) throws DAOException {
        try {
            return sqlSessionTemplate.delete("com.gjt.hellosb.dao.UserMapper.deleteByPrimaryKey", id);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
    public int insert(User user) throws DAOException {
        try {
            return sqlSessionTemplate.insert("com.gjt.hellosb.dao.UserMapper.insert", user);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
    public int insertSelective(User user) throws DAOException {
        try {
            return sqlSessionTemplate.insert("com.gjt.hellosb.dao.UserMapper.insertSelective", user);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
    public int updateByPrimaryKeySelective(User user) throws DAOException {
        try {
            return sqlSessionTemplate.update("com.gjt.hellosb.dao.UserMapper.updateByPrimaryKeySelective", user);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    
}
