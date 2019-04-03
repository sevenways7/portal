package spring.web.application.portal.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.web.application.portal.dao.CommentsDao;
import spring.web.application.portal.model.db.CommentEntity;

import javax.transaction.Transactional;
import java.util.List;

@Service("CommentsService")
@Transactional
public class CommentsService {
    @Autowired
    private CommentsDao commentsDao;

    public List<CommentEntity> getComments() {
        return commentsDao.getComments();
    }

    public void addComment(CommentEntity commentEntity) {
        commentsDao.addComment(commentEntity);
    }

    public void deleteComment(String commentId) {
        commentsDao.deleteComment(commentId);
    }

    public void deleteAllComments() {
        commentsDao.deleteAllComments();
    }
}
