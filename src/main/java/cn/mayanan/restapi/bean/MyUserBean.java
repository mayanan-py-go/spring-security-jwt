package cn.mayanan.restapi.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUserBean {
    private int id;
    private String name;
    private Department department;
}

@Getter
@Setter
class Department {
    private int id;
    private String name;
}
