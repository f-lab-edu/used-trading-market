package org.flab.hyunsb.framework.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.flab.hyunsb.framework.persistence.entity.member.MemberEntity;
import org.flab.hyunsb.framework.persistence.entity.region.RegionEntity;
import org.flab.hyunsb.framework.persistence.repository.MemberRepository;
import org.flab.hyunsb.framework.persistence.repository.RegionRepository;
import org.flab.hyunsb.framework.repository.annotation.RepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RegionRepository regionRepository;

    RegionEntity testRegion;

    @BeforeEach
    void setUp() {
        RegionEntity region = new RegionEntity(null, "경기도", "시흥시", 0.0, 0.0);
        region = regionRepository.save(region);
        testRegion = region;
    }

    @AfterEach
    void tearDown() {
        regionRepository.deleteAll();
    }

    @Test
    @DisplayName("MemberRepository Entity 매핑 테스트")
    public void Test() {
        // Given
        MemberEntity memberEntity =
            generateTestMemberEntity("test@email", "password", "nickname");
        // When
        MemberEntity expected = memberRepository.save(memberEntity);
        MemberEntity actual = memberRepository.findById(expected.getId()).get();
        // Then
        assertAll(
            () -> Assertions.assertEquals(expected.getId(), actual.getId()),
            () -> Assertions.assertEquals(expected.getPassword(), actual.getPassword()),
            () -> Assertions.assertEquals(expected.getNickname(), actual.getNickname())
        );
    }

    private MemberEntity generateTestMemberEntity(String email, String password, String nickname) {
        return new MemberEntity(null, testRegion, email, password, nickname);
    }
}