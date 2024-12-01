package redditclone.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redditclone.dto.SubredditDto;
import redditclone.service.SubredditService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        SubredditDto savedSubreddit = subredditService.save(subredditDto);
        System.out.printf("SAVE : " + savedSubreddit);
        return ResponseEntity.status(CREATED).body(savedSubreddit);
    }


    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        List<SubredditDto> subreddits = subredditService.getAll();
        return ResponseEntity.status(OK).body(subreddits);
    }
}
