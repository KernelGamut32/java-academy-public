package gts.spring.conferences.service;

import gts.spring.conferences.dto.AttendeeDTO;
import gts.spring.conferences.entity.Attendee;
import gts.spring.conferences.entity.ConferenceSession;
import gts.spring.conferences.mapper.AttendeeMapper;
import gts.spring.conferences.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final AttendeeMapper attendeeMapper;

    public List<AttendeeDTO> findAll() {
        return attendeeRepository.findAllByOrderByIdAsc()
                .stream()
                .map(attendeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AttendeeDTO findById(Long id) {
        return attendeeRepository.findById(id)
                .map(attendeeMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public AttendeeDTO create(AttendeeDTO attendeeDTO) {
        Attendee attendee = attendeeMapper.toEntity(attendeeDTO);
        return attendeeMapper.toDTO(attendeeRepository.save(attendee));
    }

    @Transactional
    public AttendeeDTO update(Long id, AttendeeDTO attendeeDTO) {
        Attendee existing = attendeeRepository.findById(id)
                .orElse(null);
        if (existing == null) {
            return null;
        }
        attendeeMapper.updateEntityFromDTO(attendeeDTO, existing);
        return attendeeMapper.toDTO(attendeeRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        Attendee attendee = attendeeRepository.findById(id).orElse(null);
        if (attendee == null) return;

        for (ConferenceSession session : attendee.getConferenceSessions()) {
            session.getAttendees().remove(attendee);
        }

        attendee.getConferenceSessions().clear();
        attendeeRepository.deleteById(id);
    }
}
