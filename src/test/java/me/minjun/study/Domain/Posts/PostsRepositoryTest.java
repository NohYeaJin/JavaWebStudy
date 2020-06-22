package me.minjun.study.Domain.Posts;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostsRepository repo;

    @After
    public void cleanUp(){
        repo.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기(){
        String title = "테스트_게시글";
        String content = "테스트_본문";

        repo.save(Posts.builder().title(title).content(content).author("rlarlejr103@gmail.com").build());

        List<Posts> postsList = repo.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle(), is(title));
        assertThat(posts.getAuthor(), is("rlarlejr103@gmail.com"));
        assertThat(posts.getContent(), is(content));
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.now();
        logger.info("BaseTimeEntity Now : " + now.toString());
        repo.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = repo.findAll();

        //then
        Posts posts = postsList.get(0);
        logger.info("BaseTimeEntity CreatedDate : " + posts.getCreatedDate());
        logger.info("BaseTimeEntity ModifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}