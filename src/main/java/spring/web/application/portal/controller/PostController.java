package spring.web.application.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.web.application.portal.service.PostService;

import javax.ws.rs.QueryParam;

@Controller
@RequestMapping("/post-service/")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "create-post", method = RequestMethod.POST)
    @ResponseBody
    public void createPost(@QueryParam("text") String text) {
        postService.createPost(text);
    }

    @RequestMapping(value = "create-comment-and-add-to-post", method = RequestMethod.POST)
    @ResponseBody
    public void createChildAndAddToParent(@QueryParam("id") String id,
                                          @QueryParam("text") String text) {
        postService.createCommentAndAddToPost(id, text);
    }

    @RequestMapping(value = "edit-comment", method = RequestMethod.POST)
    @ResponseBody
    public void editChild(@QueryParam("id") String id,
                          @QueryParam("text") String text) {
        postService.editComment(id, text);
    }

    @RequestMapping(value = "remove-comment-from-post", method = RequestMethod.POST)
    @ResponseBody
    public void removeChildFromParent(@QueryParam("commentId") String commentId) {
        postService.removeCommentFromPost(commentId);
    }

    @RequestMapping(value = "edit-post", method = RequestMethod.POST)
    @ResponseBody
    public void editPost(@QueryParam("id") String id,
                         @QueryParam("newText") String newText) {
        postService.editPost(id, newText);
    }

    @RequestMapping(value = "remove-post", method = RequestMethod.POST)
    @ResponseBody
    public void removePost(@QueryParam("id") String id) {
        postService.removePost(id);
    }
}
