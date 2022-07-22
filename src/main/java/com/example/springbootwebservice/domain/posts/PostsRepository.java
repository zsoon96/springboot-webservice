package com.example.springbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함 ( 같은 패키지 내에서 관리 )
// 둘은 아주 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로 역할 할 수 없기 때문!
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
