package com.example.springbootwebservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    // 여러 테스트가 동시에 수행될 때, db에 데이터가 그대로 남아있어 테스트 간 방해가 되기 때문에 이를 방지하고자 필요함
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "제목";
        String content = "내용";

        // id 값이 있다면 update, 없다면 insert 쿼리를 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("soon@naver.com")
                .build());

        // when
        List<Posts> postList = postsRepository.findAll();

        // then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BasicTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2022, 7, 22, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("지순")
                .build());

        // when
        List <Posts> postList = postsRepository.findAll();

        // then
        Posts post = postList.get(0);

        System.out.println(">>>>>>>>>> createDate= " + post.getCreatedDate() + ", modifiedDate= " + post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
