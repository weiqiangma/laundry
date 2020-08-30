package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.KindDao;
import com.mawkun.laundry.base.entity.Kind;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface KindDaoExt extends KindDao {

    Kind selectByName(String name);

}