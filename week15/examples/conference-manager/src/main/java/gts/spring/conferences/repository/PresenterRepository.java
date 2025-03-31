package gts.spring.conferences.repository;

import gts.spring.conferences.entity.Presenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenterRepository extends JpaRepository<Presenter, Long> {

    List<Presenter> findAllByOrderByIdAsc();
}
