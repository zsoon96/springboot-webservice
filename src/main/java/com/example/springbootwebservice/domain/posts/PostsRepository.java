package com.example.springbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함 ( 같은 패키지 내에서 관리 )
// 둘은 아주 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로 역할 할 수 없기 때문!
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // 실제 쿼리 작성 시,
    // 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 엔티티 클래스만으로 처리하기 어려워
    // 조회용 프레임워크를 추가로 사용 (querydsl, jooq, MyBtis 등)
        // 이 중에서 querydsl을 추천하는 이유
        // 1. 타입의 안정성 보장 (IDE에서 자동 검출)
        // 2. 국내 많은 회사에서 사용
        // 3. 그렇기 때문에 레퍼런스가 많음
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
