package com.louis.wenda.controller;

import com.louis.wenda.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {

    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index() {
        return "hello louis , you can use idea now";
    }

    @RequestMapping(path = "/profile/{userId}/{userName}")
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable(value = "userName", required = false) String userName,
                          @RequestParam(value = "type", defaultValue = "233") int type,
                          @RequestParam(value = "key", defaultValue = "哈哈哈", required = false) String key) {
        return String.format("hello %s, your userId is %d , type:%d, key:%s", userName, userId, type, key);
    }

    @RequestMapping(path = "/home", method = {RequestMethod.GET})
    public String template(Model model) {
        model.addAttribute("value1", "hahah");
        Map<String, String> user = new HashMap<>();
        user.put("name", "姓名");
        user.put("age", "年龄");
        user.put("sex", "性别");
        List<String> userInfo = new ArrayList<>();
        userInfo.add("张三");
        userInfo.add("19");
        userInfo.add("男");
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", new Student("Louis", "Male", 22));
        return "home";
    }

    @RequestMapping(path = {"/request"}, method = RequestMethod.GET)
    @ResponseBody
    public String request(Model model, HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getRequestURI() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        return sb.toString();
    }
}
