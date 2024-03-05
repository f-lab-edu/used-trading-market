package org.flab.hyunsb.framework.persistence.repository;

import org.flab.hyunsb.framework.persistence.entity.wishlist.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {

}
