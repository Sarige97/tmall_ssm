package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.UserService;
import com.sarige.tmall.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Transactional
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> userList = userService.list();
        int total = (int) new PageInfo<>(userList).getTotal();
        page.setTotal(total);

        model.addAttribute("userList", userList);
        model.addAttribute("page", page);
        return "admin/listUser";
    }

}
