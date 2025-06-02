package com.likelion.likelioncrud.tag.api.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record TagListResponseDto(
        List<TagInfoResponseDto> tags
) {
    public static TagListResponseDto from(List<TagInfoResponseDto> tags) {
        return TagListResponseDto.builder()
                .tags(tags)
                .build();
    }
}
