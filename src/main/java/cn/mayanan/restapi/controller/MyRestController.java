package cn.mayanan.restapi.controller;

import cn.mayanan.restapi.bean.UserBean;
import cn.mayanan.restapi.service.UserService;
import cn.mayanan.restapi.utils.JwtUtil;
import cn.mayanan.restapi.controller.LoginRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController是一个方便的注解，它结合了@Controller和@ResponseBody注解
@RestController  // 标注为Rest控制器
@RequestMapping("/api")  // 定义基础路径
public class MyRestController {
    // 创建一个logger实例
    private static final Logger logger = LogManager.getLogger(MyRestController.class);

    // 将Service注入Controller层
    private final UserService userService;
    @Autowired
    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    // 查询所有用户信息
    @GetMapping("/users")
    public PageInfo<UserBean> showUsers(
            @RequestParam(name = "page", defaultValue = "1") int pageNum,
            @RequestParam(name = "size", defaultValue = "10") int pageSize
    ) {

        // 自动分页
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<UserBean> users = userService.queryAllUser();
            return new PageInfo<>(users);
        } finally {
            PageHelper.clearPage();
        }
    }

    // 查询数据库，并返回json数据
    @GetMapping("/user/{id}")
    public ResponseEntity<UserBean> getUser(@PathVariable String id) {
        // 将id转换为int类型
        int idInt = Integer.parseInt(id);
        UserBean userBean = userService.queryUserById(idInt);
        if (userBean == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(userBean);
    }

    // 用户登录
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // @RequestBody注解会自动将请求体中的JSON转换为LoginRequest对象
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();
        System.out.println("name: " + name + ", password: " + password);
        // 调用service层的方法进行登录
        boolean success = userService.login(name, password);
        if (success) {
            // 登录成功，生成token
            String token = JwtUtil.genAccessToken(name);
            System.out.println("token: " + token);

            // 日志记录
            logger.debug("This is a debug message");
            logger.info("This is an info message");
            logger.warn("This is a warn message");
            logger.error("This is an error message");

            // 返回json数据
            return new LoginResponse("success", token);
        } else {
            return new LoginResponse("fail", null);
        }
    }

}












