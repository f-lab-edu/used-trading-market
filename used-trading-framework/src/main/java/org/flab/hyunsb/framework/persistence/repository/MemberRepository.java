package org.flab.hyunsb.framework.persistence.repository;

import java.util.Optional;
import org.flab.hyunsb.framework.persistence.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmail(String Email);
}
