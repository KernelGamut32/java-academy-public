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
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;
    private final CreatorRepository creatorRepository;

    public List<VideoGame> getAllVideoGames() {
        return videoGameRepository.findAll();
    }

    public Optional<VideoGame> getVideoGameById(Long id) {
        return videoGameRepository.findById(id);
    }

    @Transactional
    public VideoGame saveVideoGame(VideoGame videoGame) {
        List<Creator> creators = new ArrayList<>(videoGame.getCreators());
        videoGame.getCreators().clear();

        for (Creator creator : creators) {
            creator.getGames().add(videoGame);
            creatorRepository.save(creator);
            videoGame.getCreators().add(creator);
        }
        return videoGameRepository.save(videoGame);
    }

    @Transactional
    public void deleteVideoGame(Long id) {
        var videoGameReference = videoGameRepository.findById(id);
        if (videoGameReference.isPresent()) {
            VideoGame videoGame = videoGameReference.get();
            for (Creator creator : new ArrayList<>(videoGame.getCreators())) {
                videoGame.removeCreator(creator);
            }
            videoGameRepository.deleteById(id);
        }
    }
}
