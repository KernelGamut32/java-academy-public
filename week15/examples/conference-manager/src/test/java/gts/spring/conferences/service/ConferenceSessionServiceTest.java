package gts.spring.conferences.service;

import gts.spring.conferences.dto.ConferenceSessionDTO;
import gts.spring.conferences.entity.Attendee;
import gts.spring.conferences.entity.ConferenceSession;
import gts.spring.conferences.entity.Presenter;
import gts.spring.conferences.mapper.ConferenceSessionMapper;
import gts.spring.conferences.repository.AttendeeRepository;
import gts.spring.conferences.repository.ConferenceSessionRepository;
import gts.spring.conferences.repository.PresenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ConferenceSessionServiceTest {

    @Mock private ConferenceSessionRepository sessionRepository;
    @Mock private AttendeeRepository attendeeRepository;
    @Mock private PresenterRepository presenterRepository;
    @Mock private ConferenceSessionMapper sessionMapper;

    @InjectMocks
    private ConferenceSessionService sessionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnDTOList() {
        ConferenceSession session = new ConferenceSession();
        ConferenceSessionDTO dto = new ConferenceSessionDTO();
        when(sessionRepository.findAllByOrderByIdAsc()).thenReturn(List.of(session));
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        List<ConferenceSessionDTO> result = sessionService.findAll();

        assertThat(result).containsExactly(dto);
    }

    @Test
    void findById_ShouldReturnDTO_WhenFound() {
        ConferenceSession session = new ConferenceSession();
        ConferenceSessionDTO dto = new ConferenceSessionDTO();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        ConferenceSessionDTO result = sessionService.findById(1L);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        ConferenceSessionDTO result = sessionService.findById(1L);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnDTO() {
        ConferenceSessionDTO dto = new ConferenceSessionDTO();
        ConferenceSession session = new ConferenceSession();
        when(sessionMapper.toEntity(dto)).thenReturn(session);
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        ConferenceSessionDTO result = sessionService.create(dto);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void update_ShouldModifyAndReturnDTO_WhenFound() {
        ConferenceSessionDTO dto = new ConferenceSessionDTO();
        ConferenceSession session = new ConferenceSession();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        ConferenceSessionDTO result = sessionService.update(1L, dto);

        verify(sessionMapper).updateEntityFromDTO(dto, session);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void update_ShouldReturnNull_WhenNotFound() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        ConferenceSessionDTO result = sessionService.update(1L, new ConferenceSessionDTO());

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepository() {
        sessionService.delete(1L);

        verify(sessionRepository).deleteById(1L);
    }

    @Test
    void registerAttendee_ShouldAddAttendee_WhenBothExist() {
        ConferenceSession session = new ConferenceSession();
        session.setAttendees(new HashSet<>());
        Attendee attendee = new Attendee();
        ConferenceSessionDTO dto = new ConferenceSessionDTO();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(attendeeRepository.findById(2L)).thenReturn(Optional.of(attendee));
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        ConferenceSessionDTO result = sessionService.registerAttendee(1L, 2L);

        assertThat(session.getAttendees()).contains(attendee);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void registerAttendee_ShouldReturnNull_WhenSessionOrAttendeeMissing() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(sessionService.registerAttendee(1L, 2L)).isNull();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(new ConferenceSession()));
        when(attendeeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThat(sessionService.registerAttendee(1L, 2L)).isNull();
    }

    @Test
    void assignPresenter_ShouldAddPresenter_WhenBothExist() {
        ConferenceSession session = new ConferenceSession();
        session.setPresenters(new HashSet<>());
        Presenter presenter = new Presenter();
        ConferenceSessionDTO dto = new ConferenceSessionDTO();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(presenterRepository.findById(2L)).thenReturn(Optional.of(presenter));
        when(sessionRepository.save(session)).thenReturn(session);
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        ConferenceSessionDTO result = sessionService.assignPresenter(1L, 2L);

        assertThat(session.getPresenters()).contains(presenter);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void assignPresenter_ShouldReturnNull_WhenSessionOrPresenterMissing() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(sessionService.assignPresenter(1L, 2L)).isNull();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(new ConferenceSession()));
        when(presenterRepository.findById(2L)).thenReturn(Optional.empty());
        assertThat(sessionService.assignPresenter(1L, 2L)).isNull();
    }

    @Test
    void findByAttendeeId_ShouldFilterSessionsWithAttendee() {
        Attendee attendee = new Attendee(); attendee.setId(1L);
        ConferenceSession session = new ConferenceSession();
        session.setAttendees(Set.of(attendee));
        ConferenceSessionDTO dto = new ConferenceSessionDTO();

        when(sessionRepository.findAllByOrderByIdAsc()).thenReturn(List.of(session));
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        List<ConferenceSessionDTO> result = sessionService.findByAttendeeId(1L);

        assertThat(result).containsExactly(dto);
    }

    @Test
    void findByPresenterId_ShouldFilterSessionsWithPresenter() {
        Presenter presenter = new Presenter(); presenter.setId(1L);
        ConferenceSession session = new ConferenceSession();
        session.setPresenters(Set.of(presenter));
        ConferenceSessionDTO dto = new ConferenceSessionDTO();

        when(sessionRepository.findAllByOrderByIdAsc()).thenReturn(List.of(session));
        when(sessionMapper.toDTO(session)).thenReturn(dto);

        List<ConferenceSessionDTO> result = sessionService.findByPresenterId(1L);

        assertThat(result).containsExactly(dto);
    }
}
