package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResTemplate<Void>> save(@RequestBody @Valid TagSaveRequestDto requestDto) {
        tagService.save(requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.GET_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<ApiResTemplate<TagListResponseDto>> findAll() {
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResTemplate<TagInfoResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagService.findById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResTemplate<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid TagUpdateRequestDto requestDto) {
        tagService.update(id, requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.TAG_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResTemplate<Void>> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.TAG_DELETE_SUCCESS));
    }
}
