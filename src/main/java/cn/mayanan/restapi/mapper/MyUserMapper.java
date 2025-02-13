package cn.mayanan.restapi.mapper;

import cn.mayanan.restapi.bean.MyUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyUserMapper {
    MyUserBean getUserById(@Param("id") int id);
}
