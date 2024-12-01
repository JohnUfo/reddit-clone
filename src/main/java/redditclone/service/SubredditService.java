package redditclone.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redditclone.dto.SubredditDto;
import redditclone.model.Subreddit;
import redditclone.repository.SubredditRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder()
                .name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }


    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .id(subreddit.getId())
                .name(subreddit.getName())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }
}
