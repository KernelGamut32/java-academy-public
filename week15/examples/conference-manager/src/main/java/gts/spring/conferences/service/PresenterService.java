package gts.spring.conferences.service;

import gts.spring.conferences.dto.PresenterDTO;
import gts.spring.conferences.entity.ConferenceSession;
import gts.spring.conferences.entity.Presenter;
import gts.spring.conferences.mapper.PresenterMapper;
import gts.spring.conferences.repository.PresenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresenterService {

    private final PresenterRepository presenterRepository;
    private final PresenterMapper presenterMapper;

    public List<PresenterDTO> findAll() {
        return presenterRepository.findAllByOrderByIdAsc()
                .stream()
                .map(presenterMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PresenterDTO findById(Long id) {
        return presenterRepository.findById(id)
                .map(presenterMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public PresenterDTO create(PresenterDTO presenterDTO) {
        Presenter presenter = presenterMapper.toEntity(presenterDTO);
        return presenterMapper.toDTO(presenterRepository.save(presenter));
    }

    @Transactional
    public PresenterDTO update(Long id, PresenterDTO presenterDTO) {
        Presenter existing = presenterRepository.findById(id)
                .orElse(null);
        if (existing == null) {
            return null;
        }
        presenterMapper.updateEntityFromDTO(presenterDTO, existing);
        return presenterMapper.toDTO(presenterRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        Presenter presenter = presenterRepository.findById(id).orElse(null);
        if (presenter == null) return;

        for (ConferenceSession session: presenter.getConferenceSessions()) {
            session.getPresenters().remove(presenter);
        }

        presenter.getConferenceSessions().clear();
        presenterRepository.deleteById(id);
    }
}
