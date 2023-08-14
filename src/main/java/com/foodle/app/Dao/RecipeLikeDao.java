package com.foodle.app.Dao;

import com.foodle.app.Domain.RecipeLikeDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeLikeDao {
    final private SqlSession dbsession;
    String namespace = "com.foodle.app.Dao.RecipeLikeMapper.";

    @Autowired
    public RecipeLikeDao(SqlSession dbsession) {this.dbsession = dbsession;}

    public int insertLike(RecipeLikeDto likeDto) {
        return dbsession.insert(namespace + "insertLike", likeDto);
    }

    public int deleteLike(RecipeLikeDto likeDto) {
        return dbsession.delete(namespace + "deleteLike", likeDto);
    }

    public RecipeLikeDto selectOneLike(RecipeLikeDto likeDto) {
        return dbsession.selectOne(namespace + "selectOneLike", likeDto);
    }
}
