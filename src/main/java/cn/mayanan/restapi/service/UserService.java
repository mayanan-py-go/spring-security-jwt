package cn.mayanan.restapi.service;

import cn.mayanan.restapi.bean.UserBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    UserBean queryUserById(int id);

    List<UserBean> queryAllUser();

    boolean login(String name, String password);
}
