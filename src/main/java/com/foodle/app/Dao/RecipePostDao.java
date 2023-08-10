package com.foodle.app.Dao;

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


    public List<RecipePostDto> selectAllPost(){
        return dbsession.selectList(namespace + "selectAllPost");
    }

    public int insertPost(RecipePostDto recipePostDto) {
        return dbsession.insert(namespace + "insertRecipePost", recipePostDto);
    }

    public RecipePostDto selectOnePost(int bno) {
        return dbsession.selectOne(namespace + "selectOnePost", bno);
    }
}
