package com.foodle.app.Dao;

import com.foodle.app.Domain.PageInfo;
import com.foodle.app.Domain.RecipePostDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipePostDao {

    final private SqlSession dbsession;
    private String namespace = "com.foodle.app.Dao.RecipePostMapper.";

    @Autowired
    public RecipePostDao(SqlSession session) {
        this.dbsession = session;
    }


    public List<RecipePostDto> selectAllPost() {
        return dbsession.selectList(namespace + "selectAllPost");
    }

    public List<RecipePostDto> selectPosts(PageInfo pageInfo) {
        return dbsession.selectList(namespace + "selectPosts", pageInfo);
    }

    public int insertPost(RecipePostDto postDto) {
        return dbsession.insert(namespace + "insertRecipePost", postDto);
    }

    public RecipePostDto selectOnePost(int bno) {
        return dbsession.selectOne(namespace + "selectOnePost", bno);
    }

    public int deleteOnePost(int bno) {
        return dbsession.delete(namespace + "deleteOnePost", bno);
    }

    public int updateOnePost(RecipePostDto postDto) {
        return dbsession.update(namespace + "updateOnePost", postDto);
    }

    public int plusOneLike(int bno) {
        return dbsession.update(namespace + "plusOneLike", bno);
    }

    public int minusOneLike(int bno) {
        return dbsession.delete(namespace + "minusOneLike", bno);
    }

    public int selectPostLikeCount(int bno) {
        return dbsession.selectOne(namespace + "selectPostLikeCount", bno);
    }

    public int selectTotalPageCnt() {
        return dbsession.selectOne(namespace + "selectTotalPageCnt");
    }
}
