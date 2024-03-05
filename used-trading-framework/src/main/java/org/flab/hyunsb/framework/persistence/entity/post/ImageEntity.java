package org.flab.hyunsb.framework.persistence.entity.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.framework.persistence.entity.basetime.BaseTimeEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
public class ImageEntity extends BaseTimeEntity {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;

    @Column(nullable = false)
    private String imageUrl;
}
