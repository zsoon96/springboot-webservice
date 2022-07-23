package com.example.springbootwebservice.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA 엔티티 클래스들이 해당 클래스를 상속할 경우, 해당 필드들도 칼럼으로 인식하도록 설정
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능 포함시키는 설정
public abstract class BasicTimeEntity {

    @CreatedDate // 엔티티가 생성되어 저장될 때, 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 엔티티 값을 변경할 때, 시간 자동 저장
    private LocalDateTime modifiedDate;
}
