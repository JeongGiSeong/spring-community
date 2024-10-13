package com.JKS.community.board.repository;

import com.JKS.community.board.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}