package cn.mayanan.restapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.mayanan.restapi.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    UserBean getUserById(@Param("id") int id);

    List<UserBean> getAllUsers();

    UserBean getUserByName(@Param("name") String name);
}


