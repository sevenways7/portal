package spring.web.application.portal.dao;

import spring.web.application.portal.model.db.CommentEntity;
import java.util.List;

public interface CommentsDao {
    List<CommentEntity> getComments();

    void addComment(CommentEntity commentEntity);

    void deleteComment(String id);

    void deleteAllComments();
}
