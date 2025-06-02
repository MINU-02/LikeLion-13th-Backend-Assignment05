package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid TagSaveRequestDto requestDto) {
        tagService.save(requestDto);
        return new ResponseEntity<>("태그 저장!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TagListResponseDto> findAll() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagInfoResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody @Valid TagUpdateRequestDto requestDto) {
        tagService.update(id, requestDto);
        return new ResponseEntity<>("태그 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>("태그 삭제!", HttpStatus.OK);
    }
}
