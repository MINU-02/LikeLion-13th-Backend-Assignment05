package com.likelion.likelioncrud.tag.domain.repository;

import com.likelion.likelioncrud.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
