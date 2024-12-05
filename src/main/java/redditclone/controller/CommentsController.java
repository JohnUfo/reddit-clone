package redditclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redditclone.dto.CommentsDto;
import redditclone.mapper.CommentMapper;
import redditclone.model.Comment;
import redditclone.service.CommentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto) {
        return ResponseEntity.status(CREATED).body(commentService.save(commentsDto));
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String username) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(username));
    }

}
