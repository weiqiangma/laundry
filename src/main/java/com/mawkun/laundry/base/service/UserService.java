package com.mawkun.laundry.base.service;

import com.mawkun.laundry.base.dao.UserDao;
import com.mawkun.laundry.base.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:42:58
 */
@Service
public class UserService {

    @Resource(type = UserDao.class)
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public User getById(Long id) {
        return userDao.getById(id);
    }

    public User getByEntity(User user) {
        return userDao.getByEntity(user);
    }

    public List<User> listByEntity(User user) {
        return userDao.listByEntity(user);
    }

    public List<User> listByIds(List<Long> ids) {
        return userDao.listByIds(ids);
    }

    public int insert(User user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return userDao.insert(user);
    }

    public int insertBatch(List<User> list) {
        return userDao.insertBatch(list);
    }

    public int update(User user) {
        user.setUpdateTime(new Date());
        return userDao.update(user);
    }

    public int updateBatch(List<User> list) {
        return userDao.updateBatch(list);
    }

    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    public int deleteByEntity(User user) {
        return userDao.deleteByEntity(user);
    }
  
    public int deleteByIds(List<Long> list) {
        return userDao.deleteByIds(list);
    }

    public int countAll() {
        return userDao.countAll();
    }
    
    public int countByEntity(User user) {
        return userDao.countByEntity(user);
    }

}