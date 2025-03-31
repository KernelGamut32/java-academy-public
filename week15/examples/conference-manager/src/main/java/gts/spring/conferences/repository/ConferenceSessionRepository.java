package gts.spring.conferences.repository;

import gts.spring.conferences.entity.ConferenceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceSessionRepository extends JpaRepository<ConferenceSession, Long> {

    List<ConferenceSession> findAllByOrderByIdAsc();
}
