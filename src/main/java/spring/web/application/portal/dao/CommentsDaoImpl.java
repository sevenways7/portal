package spring.web.application.portal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import spring.web.application.portal.model.db.CommentEntity;
import java.util.List;

@Repository
public class CommentsDaoImpl extends AbstractDao<Integer, CommentEntity> implements CommentsDao {
    private static final Logger log = LoggerFactory.getLogger(CommentsDaoImpl.class);

    @Override
    public List<CommentEntity> getComments() {
        List<CommentEntity> list = list();
        return list;
    }

    @Override
    public void addComment(CommentEntity commentEntity) {
        persist(commentEntity);
    }

    @Override
    public void deleteComment(String id) {
        CommentEntity commentEntity = getByKey(Integer.valueOf(id));

        if (commentEntity != null) {
            log.debug("Deleting CommentEntity [ id ][ comment ][ date ] = [ " +
                    commentEntity.getId() + " ][ " +
                    commentEntity.getComment() + " ][ " +
                    commentEntity.getDate() + " ]");

            delete(commentEntity);

            log.debug("Deleted.");
        }
    }

    @Override
    public void deleteAllComments() {
        log.debug("Deleting all comments.");
        List<CommentEntity> list = list();

        for (CommentEntity commentEntity : list) {
            deleteComment(String.valueOf(commentEntity.getId()));
        }
    }
}
