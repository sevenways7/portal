package spring.web.application.portal.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.web.application.portal.model.db.CommentEntity;
import spring.web.application.portal.service.db.CommentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);
    private Gson gson = new Gson();

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/add-comment", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void addComment(HttpServletRequest request, HttpServletResponse response, @QueryParam("comment") String comment) throws IOException {
        try {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setComment(comment);
            commentEntity.setDate((new Date()).toString());

            log.debug("Adding comment: " + comment);
            commentEntity.setId(0);
            commentsService.addComment(commentEntity);
            log.debug("Comment added");

            response.getWriter().write(gson.toJson("Added comment: " + comment));
        } catch (Exception e) {
            log.error("Error adding comment", e);
        }
    }

    @RequestMapping(value = "/get-comments", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public void getComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            log.debug("Getting comments.");
            List<CommentEntity> commentEntities = commentsService.getComments();
            log.debug("Comments received.");

            response.getWriter().write(gson.toJson(commentEntities));
        } catch (Exception e) {
            log.error("Error receiving comments.", e);
        }
    }

    @RequestMapping(value = "/delete-comment-by-id", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void deleteCommentById(HttpServletRequest request, HttpServletResponse response, @QueryParam("commentId") String commentId) throws IOException {
        try {
            commentsService.deleteComment(commentId);

            response.getWriter().write(gson.toJson("Added comment: " + commentId));
        } catch (Exception e) {
            log.error("Error adding comment", e);
        }
    }

    @RequestMapping(value = "/delete-all-comments", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void deleteAllComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            commentsService.deleteAllComments();

            response.getWriter().write(gson.toJson("All comments removed."));
        } catch (Exception e) {
            log.error("Error removing comments.", e);
        }
    }
}
