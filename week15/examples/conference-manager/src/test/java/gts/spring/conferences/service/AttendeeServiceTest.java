package gts.spring.conferences.service;

import gts.spring.conferences.dto.AttendeeDTO;
import gts.spring.conferences.entity.Attendee;
import gts.spring.conferences.mapper.AttendeeMapper;
import gts.spring.conferences.repository.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AttendeeServiceTest {

    @Mock
    private AttendeeRepository attendeeRepository;

    @Mock
    private AttendeeMapper attendeeMapper;

    @InjectMocks
    private AttendeeService attendeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfAttendeeDTOs() {
        Attendee attendee = new Attendee();
        AttendeeDTO attendeeDTO = new AttendeeDTO();
        List<Attendee> attendees = List.of(attendee);
        List<AttendeeDTO> expected = List.of(attendeeDTO);

        when(attendeeRepository.findAllByOrderByIdAsc()).thenReturn(attendees);
        when(attendeeMapper.toDTO(attendee)).thenReturn(attendeeDTO);

        List<AttendeeDTO> result = attendeeService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(attendeeRepository).findAllByOrderByIdAsc();
    }

    @Test
    void findById_ShouldReturnAttendeeDTO_WhenFound() {
        Long id = 1L;
        Attendee attendee = new Attendee();
        AttendeeDTO attendeeDTO = new AttendeeDTO();

        when(attendeeRepository.findById(id)).thenReturn(Optional.of(attendee));
        when(attendeeMapper.toDTO(attendee)).thenReturn(attendeeDTO);

        AttendeeDTO result = attendeeService.findById(id);

        assertThat(result).isEqualTo(attendeeDTO);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        Long id = 1L;

        when(attendeeRepository.findById(id)).thenReturn(Optional.empty());

        AttendeeDTO result = attendeeService.findById(id);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnAttendeeDTO() {
        AttendeeDTO dto = new AttendeeDTO();
        Attendee entity = new Attendee();

        when(attendeeMapper.toEntity(dto)).thenReturn(entity);
        when(attendeeRepository.save(entity)).thenReturn(entity);
        when(attendeeMapper.toDTO(entity)).thenReturn(dto);

        AttendeeDTO result = attendeeService.create(dto);

        assertThat(result).isEqualTo(dto);
        verify(attendeeRepository).save(entity);
    }

    @Test
    void update_ShouldModifyAndReturnUpdatedAttendeeDTO_WhenFound() {
        Long id = 1L;
        AttendeeDTO dto = new AttendeeDTO();
        Attendee entity = new Attendee();

        when(attendeeRepository.findById(id)).thenReturn(Optional.of(entity));
        when(attendeeRepository.save(entity)).thenReturn(entity);
        when(attendeeMapper.toDTO(entity)).thenReturn(dto);

        AttendeeDTO result = attendeeService.update(id, dto);

        verify(attendeeMapper).updateEntityFromDTO(dto, entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void update_ShouldReturnNull_WhenAttendeeNotFound() {
        Long id = 1L;
        AttendeeDTO dto = new AttendeeDTO();

        when(attendeeRepository.findById(id)).thenReturn(Optional.empty());

        AttendeeDTO result = attendeeService.update(id, dto);

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        Long id = 1L;
        Attendee entity = new Attendee();

        when(attendeeRepository.findById(id)).thenReturn(Optional.of(entity));

        attendeeService.delete(id);

        verify(attendeeRepository).findById(id);
        verify(attendeeRepository).deleteById(id);
    }
}
