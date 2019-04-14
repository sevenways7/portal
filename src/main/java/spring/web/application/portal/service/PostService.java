package spring.web.application.portal.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.application.portal.model.db.Comment;
import spring.web.application.portal.model.db.Post;
import spring.web.application.portal.model.view.CommentElement;
import spring.web.application.portal.model.view.PageElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("postService")
@Transactional
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void createPost(String text) {
        Post post = new Post();
        post.setText(text);

        Session session =
                sessionFactory.getCurrentSession();

        session.save(post);
    }

    public void editPost(String postId, String newText) {
        Session session =
                sessionFactory.getCurrentSession();

        Post post = (Post) session.get(Post.class, Integer.valueOf(postId));

        if (post != null) {
            post.setText(newText);
            //Don't need save.
            //session.saveOrUpdate(post);
        } else {
            log.debug("Post with ID = " + postId + " not exist.");
        }
    }

    public void removePost(String postId) {
        Session session =
                sessionFactory.getCurrentSession();

        Post post = (Post) session.get(Post.class, Integer.valueOf(postId));

        if (post != null) {
            session.delete(post);
        } else {
            log.debug("Post with ID = " + postId + " not exist.");
        }
    }

    public void createCommentAndAddToPost(String postId, String commentText) {
        log.debug(String.format("Adding COMMENT to post with ID = \"%s\". " +
                "Comment text =  \"%s\"...", postId, commentText));

        Session session =
                sessionFactory.getCurrentSession();

        Post post =
                (Post) session.get(Post.class, Integer.valueOf(postId));

        // Now "post" is not simple element.
        // It is under the supervision of Hibernate.
        if (post != null) {
            Comment comment = new Comment();
            comment.setText(commentText);
            comment.setPost(post);

            // Need save if not use
            // "cascade = CascadeType.ALL"
            //session.save(comment);

            post.getComments().add(comment);

            // Don't need any saves to DB,
            // because in Post.class
            // used "cascade = CascadeType.ALL"
        } else {
            log.debug("Error. Post not found.");
        }
    }

    public void editComment(String commentId, String newText) {
        log.debug(String.format("Updating comment ID \"%s\"...", commentId));

        Session session =
                sessionFactory.getCurrentSession();

        Comment comment =
                (Comment) session.get(Comment.class, Integer.valueOf(commentId));

        if (comment != null) {
            comment.setText(newText);
            //session.saveOrUpdate(comment);
        } else {
            log.debug("Error. Post not found.");
        }
    }

    public void removeCommentFromPost(String commentId) {
        Session session =
                sessionFactory.getCurrentSession();

        Comment comment =
                (Comment) session.get(Comment.class, Integer.valueOf(commentId));

        if (comment != null) {
            Post post = comment.getPost();

            post.getComments().remove(comment);
            //session.save(post);
            //session.saveOrUpdate(comment);
        } else {
            log.debug("Error. Comment not found.");
        }

    }

    public List<PageElement> getAllPosts() {
        List<PageElement> result = new ArrayList<>();

        Session session =
                sessionFactory.getCurrentSession();

        //@SuppressWarnings("unchecked")
        List<Post> posts = session
                .createSQLQuery("select * from post")
                .addEntity(Post.class)
                .list();

        // Criteria criteria = session.createCriteria(Post.class);
        // Return duplicates.
        // List<Post> posts = (List<Post>) criteria.list();

        for (Post post : posts) {
            PageElement pageElement = new PageElement();

            pageElement.setId(post.getId());
            pageElement.setText(post.getText());

            List<CommentElement> comments = post.getComments().stream()
                    .map(comment -> new CommentElement(comment.getId(), comment.getText()))
                    .collect(Collectors.toList());

            pageElement.setComments(comments);

            result.add(pageElement);
        }

        return result;
    }
}
