package gts.spring.games.services;

import gts.spring.games.domain.Creator;
import gts.spring.games.domain.VideoGame;
import gts.spring.games.repositories.jpa.CreatorRepository;
import gts.spring.games.repositories.jpa.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final VideoGameRepository videoGameRepository;

    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }

    public Optional<Creator> getCreatorById(Long id) {
        return creatorRepository.findById(id);
    }

    @Transactional
    public Creator saveCreator(Creator creator) {
        return creatorRepository.save(creator);
    }

    @Transactional
    public void deleteCreator(Long id) {
        var creatorReference = creatorRepository.findById(id);
        if (creatorReference.isPresent()) {
            creatorRepository.deleteById(id);
        }
    }
}
