package com.likelion.likelioncrud.tag.application;

import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.common.exception.BusinessException;
import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.domain.Tag;
import com.likelion.likelioncrud.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public void save(TagSaveRequestDto requestDto) {
        Tag tag = Tag.builder()
                .name(requestDto.name())
                .build();
        tagRepository.save(tag);
    }

    public TagListResponseDto findAll() {
        List<Tag> tags = tagRepository.findAll();
        List<TagInfoResponseDto> dtoList = tags.stream()
                .map(TagInfoResponseDto::from)
                .toList();
        return TagListResponseDto.from(dtoList);
    }

    public TagInfoResponseDto findById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.TAG_NOT_FOUND_EXCEPTION,
                        "Tag not found: " + id
                ));
        return TagInfoResponseDto.from(tag);
    }

    @Transactional
    public void update(Long id, TagUpdateRequestDto requestDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.TAG_NOT_FOUND_EXCEPTION,
                        "Tag not found: " + id
                ));
        tag.updateName(requestDto.name());
    }

    @Transactional
    public void delete(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.TAG_NOT_FOUND_EXCEPTION,
                        "Tag not found: " + id
                ));
        tagRepository.delete(tag);
    }
}
