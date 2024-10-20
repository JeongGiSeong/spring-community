package com.JKS.community.board.domain;

import com.JKS.community.user.domain.Member;
import com.JKS.community.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comment SET enabled = false WHERE comment_id = ?")
@SQLRestriction("enabled = true")
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @NotNull
    @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String content;
    @Setter
    private int likeCount = 0;
    @Setter
    private int dislikeCount = 0;
    private int level = 0;
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    public static Comment of(Comment parent, int level, Post post, Member member, String content) {
        Comment comment = new Comment();
        comment.parent = parent;
        comment.level = level;
        comment.post = post;
        comment.member = member;
        comment.content = content;

        post.addComment(comment);
        if (parent != null) {
            parent.getChildren().add(comment);
        }
        return comment;
    }

    public void update(String content) {
        this.content = content;
    }

}
