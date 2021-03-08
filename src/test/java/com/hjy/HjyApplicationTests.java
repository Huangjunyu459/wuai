package com.hjy;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

/*    @Test
    void testRecharge() {
        System.out.println(userController.recharge(1365472501483728897L));
    }*/


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
        List<User> userList = userService.findUsersByUsername("sam");
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

/*    @Test
    public void testSignIn() {
        userService.signIn(1365502991892807681L);
    }*/




    @Test
    public void testFindByEmail() {
        System.out.println(userController.findUserByEmail("541605007@qq.com"));
        System.out.println(userController.findUserByEmail("qz@qq.com"));
    }

//    @Test
//    public void testFindByMap() {
//        NameAndEmail entity = new NameAndEmail(null, "qz@qq.com");
//        List<User> userList = userService.findByMap(entity);
//        Iterator<User> iterator = userList.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }

    @Test
    void testFindIsDelete() {
        System.out.println(userController.findIsDelete());
        List<User> isDelete = userService.findIsDelete();
        Iterator<User> iterator = isDelete.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //  分页查询
    @Test
    public void testPage() {
        //  参数1：当前页     参数2：页面大小
//        Page<User> page = new Page<>(1, 5);
//        IPage<User> userIPage = userMapper.selectPage(page, null);
//
//        page.getRecords().forEach(System.out::println);
//        System.out.println(page.getTotal());

    }


}
