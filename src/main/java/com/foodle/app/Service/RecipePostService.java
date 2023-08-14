package com.foodle.app.Service;

import com.foodle.app.Dao.RecipeLikeDao;
import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.RecipeLikeDto;
import com.foodle.app.Domain.RecipePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipePostService {
    final private RecipePostDao postDao;
    final private RecipeLikeDao likeDao;

    @Autowired
    public RecipePostService(RecipePostDao postDao,RecipeLikeDao likeDao) {
        this.postDao = postDao;
        this.likeDao = likeDao;
    }

    public int writePost(RecipePostDto post) {
        return postDao.insertPost(post);
    }

    public RecipePostDto getOnePost(int bno) {
        return postDao.selectOnePost(bno);
    }

    public boolean isUserEqualWriter(String userName, int bno){
        RecipePostDto post = postDao.selectOnePost(bno);
        return userName.equals(post.getWriter());
    }

    public int deleteOnePost(int bno) {
        return postDao.deleteOnePost(bno);
    }

    public int modifyOnePost(RecipePostDto post) {
        return postDao.updateOnePost(post);
    }


    public boolean isLikeExist(RecipeLikeDto likeDto) {
        RecipeLikeDto dblike = likeDao.selectOneLike(likeDto);
        if( dblike == null)
            return false;
        else
            return true;
    }
    public boolean increaseLike(RecipeLikeDto likeDto) {
        if(1 == likeDao.insertLike(likeDto)){
            postDao.plusOneLike(likeDto.getBno());
            return true; // success
        }
        return false; // fail
    }

    public boolean decraseLike(RecipeLikeDto likeDto) {
        if (1 == likeDao.deleteLike(likeDto)) {
            postDao.minusOneLike(likeDto.getBno());
            return true;
        }
        return false;
    }

    public int getPostLikeCount(int bno) {
        return postDao.selectPostLikeCount(bno);
    }

}
