package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Comment Respource")
@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Comments By Post ID REST API")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getcommnetsbyPostId(@PathVariable(value = "postId") long postId)
    {
        return  commentService.getCommentsByPostId(postId);
    }

    @ApiOperation(value = "Get Single Comment By ID REST API")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId)
    {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Comment By ID REST API")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId,@Valid @RequestBody CommentDto commentDto)
    {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Comment By ID REST API")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commentId)
    {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment Entity Deleted Successfully", HttpStatus.OK);

    }
}
