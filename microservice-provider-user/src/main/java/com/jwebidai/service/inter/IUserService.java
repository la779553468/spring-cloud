package com.jwebidai.service.inter;

import com.jwebidai.model.User;

/**
 * Created by wjh on 2018/2/27.
 */
public interface IUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
