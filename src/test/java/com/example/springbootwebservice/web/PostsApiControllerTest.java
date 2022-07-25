package com.example.springbootwebservice.web;

import com.example.springbootwebservice.domain.posts.Posts;
import com.example.springbootwebservice.domain.posts.PostsRepository;
import com.example.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.springbootwebservice.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 랜덤 포트로 설정
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate; // JPA 기능까지 한번에 테스트할 때 사용

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    // 매번 테스트가 시작되기 전에 MockMvc 인스턴스 생성 > 그래야지 @WithMockUser가 작동됨
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles="USER") // 인증된 가짜 사용자 설정
    public void Posts_등록() throws Exception {
        // given
        String title = "제목";
        String content = "내용";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("지순")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        mvc.perform(post(url) // 생성된 mvc를 통해 API 테스트
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto))) // Body 영역은 문자열로 표현하기 위해 ObjectMapper를 통해 문자열 JSON으로 변환
                .andExpect(status().isOk());
        //ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        // then
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    @WithMockUser(roles="USER")
    public void Posts_수정() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("지순")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "제목 수정";
        String expectedContent = "내용 수정";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity <PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        mvc.perform(put(url) // 생성된 mvc를 통해 API 테스트
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postList = postsRepository.findAll();
        assertThat(postList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(postList.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    @WithMockUser(roles="USER")
    public void Posts_삭제() throws Exception {
        // given
        Posts post = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("지순")
                .build());

        Long deleteId = post.getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + deleteId;

        HttpEntity<Posts> savedEntity = new HttpEntity<>(post);

        // when
        mvc.perform(delete(url) // 생성된 mvc를 통해 API 테스트
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
        //ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, savedEntity, Long.class);

        // then
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> result = postsRepository.findAll();
//        assertThat(result.size()).isEqualTo(0); // 방법 1
        assertThat(result).isEmpty(); // 방법 2
    }
}
