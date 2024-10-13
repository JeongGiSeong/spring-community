package com.JKS.community.board.repository;

import com.JKS.community.board.domain.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByMemberIdAndPostId(Long memberId, Long postId);

    Optional<Reaction> findByMemberIdAndCommentId(Long memberId, Long commentId);
}
