package dev.ngdangkiet.repository;

import dev.ngdangkiet.domain.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ngdangkiet
 * @since 10/31/2023
 */

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
}
