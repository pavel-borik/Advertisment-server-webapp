package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.Comment;

public interface CommentRepository {
    void save(Comment comment);
}
