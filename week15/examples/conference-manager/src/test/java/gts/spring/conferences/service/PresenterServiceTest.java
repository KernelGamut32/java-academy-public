package gts.spring.conferences.service;

import gts.spring.conferences.dto.PresenterDTO;
import gts.spring.conferences.entity.Presenter;
import gts.spring.conferences.mapper.PresenterMapper;
import gts.spring.conferences.repository.PresenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PresenterServiceTest {

    @Mock
    private PresenterRepository presenterRepository;

    @Mock
    private PresenterMapper presenterMapper;

    @InjectMocks
    private PresenterService presenterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfPresenterDTOs() {
        Presenter presenter = new Presenter();
        PresenterDTO presenterDTO = new PresenterDTO();
        List<Presenter> presenters = List.of(presenter);
        List<PresenterDTO> expected = List.of(presenterDTO);

        when(presenterRepository.findAllByOrderByIdAsc()).thenReturn(presenters);
        when(presenterMapper.toDTO(presenter)).thenReturn(presenterDTO);

        List<PresenterDTO> result = presenterService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(presenterRepository).findAllByOrderByIdAsc();
    }

    @Test
    void findById_ShouldReturnPresenterDTO_WhenFound() {
        Long id = 1L;
        Presenter presenter = new Presenter();
        PresenterDTO presenterDTO = new PresenterDTO();

        when(presenterRepository.findById(id)).thenReturn(Optional.of(presenter));
        when(presenterMapper.toDTO(presenter)).thenReturn(presenterDTO);

        PresenterDTO result = presenterService.findById(id);

        assertThat(result).isEqualTo(presenterDTO);
    }

    @Test
    void findById_ShouldReturnNull_WhenNotFound() {
        Long id = 1L;

        when(presenterRepository.findById(id)).thenReturn(Optional.empty());

        PresenterDTO result = presenterService.findById(id);

        assertThat(result).isNull();
    }

    @Test
    void create_ShouldSaveAndReturnPresenterDTO() {
        PresenterDTO dto = new PresenterDTO();
        Presenter entity = new Presenter();

        when(presenterMapper.toEntity(dto)).thenReturn(entity);
        when(presenterRepository.save(entity)).thenReturn(entity);
        when(presenterMapper.toDTO(entity)).thenReturn(dto);

        PresenterDTO result = presenterService.create(dto);

        assertThat(result).isEqualTo(dto);
        verify(presenterRepository).save(entity);
    }

    @Test
    void update_ShouldModifyAndReturnUpdatedPresenterDTO_WhenFound() {
        Long id = 1L;
        PresenterDTO dto = new PresenterDTO();
        Presenter entity = new Presenter();

        when(presenterRepository.findById(id)).thenReturn(Optional.of(entity));
        when(presenterRepository.save(entity)).thenReturn(entity);
        when(presenterMapper.toDTO(entity)).thenReturn(dto);

        PresenterDTO result = presenterService.update(id, dto);

        verify(presenterMapper).updateEntityFromDTO(dto, entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void update_ShouldReturnNull_WhenPresenterNotFound() {
        Long id = 1L;
        PresenterDTO dto = new PresenterDTO();

        when(presenterRepository.findById(id)).thenReturn(Optional.empty());

        PresenterDTO result = presenterService.update(id, dto);

        assertThat(result).isNull();
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        Long id = 1L;
        Presenter presenter = new Presenter();

        when(presenterRepository.findById(id)).thenReturn(Optional.of(presenter));

        presenterService.delete(id);

        verify(presenterRepository).findById(id);
        verify(presenterRepository).deleteById(id);
    }
}
