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
        User user = userMapper.selectById(1365502991892807681L);
        user.setUsername("xg");
        user.setPassword("xg");
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
    void testRecharge() {
        System.out.println(userController.recharge(1365472501483728897L));
    }


    @Test
    public void testFindAllUser() {
        System.out.println(userController.findAllUser());
        List<User> userList = userService.findAllUser();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testFindUserByUsername() {
        System.out.println(userController.findUserByUsername("sam"));
        List<User> userList = userService.findUserByUsername("sam");
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testSignIn() {
        userService.signIn(1365502991892807681L);
    }


    @Test
    public void testFindUserByIdOrUsername() {
        //List<User> userList = userService.findUserByIdOrUsername(1365502991892807681L);
        List<User> userList = userService.findUserByIdOrUsername("sam");
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        String page = userController.findUserByIdOrUsername("sam");
        System.out.println(page);
    }


}
