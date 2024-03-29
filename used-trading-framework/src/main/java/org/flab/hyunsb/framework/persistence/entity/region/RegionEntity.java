package org.flab.hyunsb.framework.persistence.entity.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.domain.region.Region;
import org.flab.hyunsb.framework.persistence.entity.basetime.BaseTimeEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "region")
public class RegionEntity extends BaseTimeEntity {

    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sido;

    @Column(nullable = false)
    private String sigungu;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;

    public static RegionEntity valueOf(Long regionId) {
        return RegionEntity.builder().id(regionId).build();
    }

    public Region toDomain() {
        return new Region(id, sido, sigungu, lat, lng);
    }
}
