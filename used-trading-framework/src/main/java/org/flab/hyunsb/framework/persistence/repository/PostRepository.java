package org.flab.hyunsb.framework.persistence.repository;

import org.flab.hyunsb.framework.persistence.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
