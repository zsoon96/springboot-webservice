package com.example.springbootwebservice.domain.posts.user;

import com.example.springbootwebservice.domain.posts.BasicTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) // JPA로 DB 저장할 때, Enum 값을 어떤 형태로 저장할지 설정 ( 기본 int > String으로 변경 )
    @Column(nullable = false)
    private Role role;
}
