package cn.mayanan.restapi.controller;

import cn.mayanan.restapi.bean.MyUserBean;
import cn.mayanan.restapi.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyUserController {
    // 将Service注入Controller层
    private final MyUserService myUserService;
    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }
    @GetMapping("/user-depart/{id}")
    public MyUserBean getUserDepartment(@PathVariable int id) {
        return myUserService.queryUserById(id);
    }
}









