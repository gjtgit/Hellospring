package com.gjt.hellosb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.gjt.hellosb.entity.User;
import com.gjt.hellosb.exception.DAOException;

@Repository
@Mapper
public interface UserMapper {
    User selectByPrimaryKey(int id);
    List<User> getAllUsers();
    boolean deleteByPrimaryKey(int id);
    public int insert(User user);
    public int insertSelective(User user);
    public int updateByPrimaryKeySelective(User user);
}
