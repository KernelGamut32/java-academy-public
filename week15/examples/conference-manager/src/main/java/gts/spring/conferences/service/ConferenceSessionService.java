package gts.spring.conferences.service;

import gts.spring.conferences.dto.ConferenceSessionDTO;
import gts.spring.conferences.entity.Attendee;
import gts.spring.conferences.entity.ConferenceSession;
import gts.spring.conferences.entity.Presenter;
import gts.spring.conferences.mapper.ConferenceSessionMapper;
import gts.spring.conferences.repository.AttendeeRepository;
import gts.spring.conferences.repository.ConferenceSessionRepository;
import gts.spring.conferences.repository.PresenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceSessionService {

    private final ConferenceSessionRepository conferenceSessionRepository;
    private final AttendeeRepository attendeeRepository;
    private final PresenterRepository presenterRepository;
    private final ConferenceSessionMapper conferenceSessionMapper;

    public List<ConferenceSessionDTO> findAll() {
        return conferenceSessionRepository.findAllByOrderByIdAsc()
                .stream()
                .map(conferenceSessionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConferenceSessionDTO findById(Long id) {
        return conferenceSessionRepository.findById(id)
                .map(conferenceSessionMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public ConferenceSessionDTO create(ConferenceSessionDTO sessionDTO) {
        ConferenceSession session = conferenceSessionMapper.toEntity(sessionDTO);
        return conferenceSessionMapper.toDTO(conferenceSessionRepository.save(session));
    }

    @Transactional
    public ConferenceSessionDTO update(Long id, ConferenceSessionDTO sessionDTO) {
        ConferenceSession existing = conferenceSessionRepository.findById(id)
                .orElse(null);
        if (existing == null) {
            return null;
        }
        conferenceSessionMapper.updateEntityFromDTO(sessionDTO, existing);
        return conferenceSessionMapper.toDTO(conferenceSessionRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        conferenceSessionRepository.deleteById(id);
    }

    @Transactional
    public ConferenceSessionDTO registerAttendee(Long sessionId, Long attendeeId) {
        ConferenceSession session = conferenceSessionRepository.findById(sessionId)
                .orElse(null);
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElse(null);
        if (session == null || attendee == null) {
            return null;
        }
        session.getAttendees().add(attendee);
        return conferenceSessionMapper.toDTO(conferenceSessionRepository.save(session));
    }

    @Transactional
    public ConferenceSessionDTO assignPresenter(Long sessionId, Long presenterId) {
        ConferenceSession session = conferenceSessionRepository.findById(sessionId)
                .orElse(null);
        Presenter presenter = presenterRepository.findById(presenterId)
                .orElse(null);
        if (session == null || presenter == null) {
            return null;
        }
        session.getPresenters().add(presenter);
        return conferenceSessionMapper.toDTO(conferenceSessionRepository.save(session));
    }

    public List<ConferenceSessionDTO> findByAttendeeId(Long attendeeId) {
        return conferenceSessionRepository.findAllByOrderByIdAsc().stream()
                .filter(session -> session.getAttendees().stream()
                        .anyMatch(attendee -> attendee.getId().equals(attendeeId)))
                .map(conferenceSessionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ConferenceSessionDTO> findByPresenterId(Long presenterId) {
        return conferenceSessionRepository.findAllByOrderByIdAsc().stream()
                .filter(session -> session.getPresenters().stream()
                        .anyMatch(presenter -> presenter.getId().equals(presenterId)))
                .map(conferenceSessionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
