package com.example.springbootwebservice.service.posts;

import com.example.springbootwebservice.domain.posts.Posts;
import com.example.springbootwebservice.domain.posts.PostsRepository;
import com.example.springbootwebservice.web.dto.PostsResponseDto;
import com.example.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
// 서비스단은 비즈니스 로직을 처리하는 곳이 아닌 트랜잭션 / 도메인 간 순서 보장의 역할만 함!!!
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 시, 쿼리를 날리는 부분이 없는 이유는 JPA의 영속성 컨텍스트 때문 !
    // 트랜잭션 안에서 db에서 가져온 데이터는 영속성 컨텍스트(영구 저장 환경)가 유지된 상태
    // 그래서 영속성 컨텍스트가 유지된 상태의 데이터 값을 변경하면, 트랜잭션이 끝나는 시점에 해당 테이블에게 변경분을 모두 반영하기 때문에
    // 엔티티 객체의 값만 변경해놓으면, 알아서 업데이트 쿼리를 날려줌 ( = 더티 체킹 )
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id)
        );

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto show (Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id)
        );

        return new PostsResponseDto(post);
    }

}
