package com.hjy;

import com.hjy.wuai.controller.UserController;
import com.hjy.wuai.mapper.*;
import com.hjy.wuai.pojo.*;
import com.hjy.wuai.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest
class HjyApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserController userController;

    @Autowired
    private RolesMapper rolesMapper;



    @Test
    void contextLoads() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPasswordSalt("bbbbb");
        user.setAvatar("ccccccccccc");

        userMapper.insert(user);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void testService() {

        User user = new User();
        user.setUsername("body1111");
        user.setPassword("bodyzz");
        user.setPasswordSalt("bbbbb");
        user.setAvatar("ccccccccccc");

        userService.save(user);

    }


    @Test
    void testCon() {

        User user = new User();
        user.setUsername("lucky");
        user.setPassword("good");
        user.setPasswordSalt("badtaste");
        user.setAvatar("hhhhhhh");


    }

    @Test
    public void testFindById() {
        Long id = 1363146042106732545L;
        User user = userService.getById(id);
        System.out.println(user);
        String msg = userController.findUserById(id);
        System.out.println(msg);

    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1365470493997563906L);
        user.setUsername("zzw");
        user.setPassword("zzw");
        System.out.println(userController.updateUser(user));
    }


    @Test
    void testRole() {
        Roles roles = new Roles();
//        roles.setRoleId(1);
//        roles.setRoleName("normal");

//        roles.setRoleId(2);
//        roles.setRoleName("vip");

    }

    @Test
    void testRecharge(){
        System.out.println(userController.recharge(1365472501483728897L));
    }


}
