package cn.mayanan.restapi.serviceImpl;

import cn.mayanan.restapi.bean.UserBean;
import cn.mayanan.restapi.mapper.UserMapper;
import cn.mayanan.restapi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // 将Dao注入Service层
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 根据用户id查询用户信息
    @Override  // 重写接口方法
    public UserBean queryUserById(int id) {
        return userMapper.getUserById(id);
    }
    // 查询所有用户信息
    @Override
    public List<UserBean> queryAllUser() {
        // 开始分页
        return userMapper.getAllUsers();
    }
    // 用户登录
    @Override
    public boolean login(String name, String password) {
        UserBean userBean = userMapper.getUserByName(name);
        if (userBean == null) {
            return false;
        } else {
            return userBean.getPassword().equals(password);
        }
    }
}





