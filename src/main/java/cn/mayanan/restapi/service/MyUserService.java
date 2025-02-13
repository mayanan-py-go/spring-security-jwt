package cn.mayanan.restapi.service;

import cn.mayanan.restapi.bean.MyUserBean;

public interface MyUserService {
    MyUserBean queryUserById(int id);
}
