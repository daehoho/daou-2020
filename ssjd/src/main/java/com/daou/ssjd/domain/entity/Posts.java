package com.daou.ssjd.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "problem_id")
    private Problems problem;

    @OneToMany(mappedBy = "posts", fetch = EAGER)
    private List<Messages> messages = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "code")
    @Lob
    private String code;

    @Builder
    public Posts(Users user, Problems problem, String language, String title,
                 String content, String code) {
        this.user = user;
        this.problem = problem;
        this.language = language;
        this.title = title;
        this.content = content;
        this.code = code;
    }

    public void update(Users user, Problems problem, List<Messages> messages,
                       String language, String title, String content, String code) {
        this.user = user;
        this.problem = problem;
        this.messages = messages;
        this.language = language;
        this.title = title;
        this.content = content;
        this.code = code;
        this.updateModifiedDate(LocalDateTime.now());
    }
}
