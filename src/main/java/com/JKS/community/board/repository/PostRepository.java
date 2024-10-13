package com.JKS.community.board.repository;

import com.JKS.community.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);

    // 제목이나 내용에 키워드가 포함되면서 동시에 활성화된 게시글 목록을 조회하는 메서드
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    Page<Post> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findAllByCategoryIdIn(List<Long> ids, Pageable pageable);

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);
}
