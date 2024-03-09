package org.flab.hyunsb.framework.persistence.adapter;

import java.util.Optional;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.flab.hyunsb.framework.persistence.entity.region.RegionEntity;
import org.flab.hyunsb.framework.persistence.repository.RegionRepository;
import org.flab.hyunsb.framework.repository.annotation.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
class MemberPersistenceAdapterTest {

    @Autowired
    private MemberPersistenceAdapter memberPersistenceAdapter;

    private RegionEntity testRegionEntity;

    @BeforeEach
    void setUp(@Autowired RegionRepository regionRepository) {
        RegionEntity region = new RegionEntity(1L, "경기도", "시흥시", 0.0, 0.0);
        testRegionEntity = regionRepository.save(region);
    }
    
    @Test
    @DisplayName("[회원 등록 성공 테스트] 회원 등록 도메인이 주어졌을 시, 회원을 등록한 뒤 도메인 객체를 반환한다.")
    public void saveMember_successTest() {
        // Given
        MemberForCreate memberForCreate = generateTestMemberForCreate();
        // When
        Member actualMember = memberPersistenceAdapter.saveMember(memberForCreate);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertNotNull(actualMember.id()),
            () -> Assertions.assertEquals(memberForCreate.getEmail(), actualMember.email()),
            () -> Assertions.assertEquals(memberForCreate.getPassword(), actualMember.password()),
            () -> Assertions.assertEquals(memberForCreate.getNickname(), actualMember.nickname())
        );
    }

    private MemberForCreate generateTestMemberForCreate() {
        Long regionId = testRegionEntity.getId();
        String email = "email@email";
        String password = "password";
        String nickname = "nickname";
        return new MemberForCreate(regionId, email, password, nickname);
    }

    @Test
    @DisplayName("[회원 이메일 조회 성공 테스트] 매핑되는 이메일이 존재할 시, 도메인 객체를 반환한다.")
    public void findMemberByEmail_successTest() {
        // Given
        Member member = memberPersistenceAdapter.saveMember(generateTestMemberForCreate());
        // When 
        Optional<Member> optionalMember = memberPersistenceAdapter.findByEmail(member.email());
        // Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(optionalMember.isPresent()),
            () -> Assertions.assertEquals(member.id(), optionalMember.get().id())
        );
    }

    @Test
    @DisplayName("[회원 이메일 조회 성공 테스트] 매핑되는 이메일이 존재하지 않을 시, 빈 Optional 객체를 반환한다.")
    public void findMemberByEmail_successTest_memberNotExist() {
        // Given
        String invalidEmail = "invalid@Email";
        // When
        Optional<Member> optionalMember = memberPersistenceAdapter.findByEmail(invalidEmail);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(optionalMember.isEmpty())
        );
    }
}
