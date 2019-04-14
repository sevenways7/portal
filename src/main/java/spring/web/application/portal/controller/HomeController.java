package spring.web.application.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.web.application.portal.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    PostService postService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap map, HttpServletRequest request) {
        map.addAttribute("postList", postService.getAllPosts());

        return "index";
    }
}
