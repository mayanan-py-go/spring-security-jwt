package cn.mayanan.restapi.serviceImpl;

import cn.mayanan.restapi.bean.MyUserBean;
import cn.mayanan.restapi.mapper.MyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayanan.restapi.service.MyUserService;

@Service
public class MyUserServiceImpl implements MyUserService {
    // 将Dao注入到Service层
    private final MyUserMapper myUserMapper;
    @Autowired
    public MyUserServiceImpl (MyUserMapper myUserMapper) {
        this.myUserMapper = myUserMapper;
    }

    public MyUserBean queryUserById(int id) {
        return myUserMapper.getUserById(id);
    }
}
