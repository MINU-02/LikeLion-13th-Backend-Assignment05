package com.likelion.likelioncrud.member.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.likelioncrud.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.likelioncrud.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.likelioncrud.member.api.dto.response.MemberListResponseDto;
import com.likelion.likelioncrud.member.application.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping
    public ResponseEntity<ApiResTemplate<Void>> createMember(@RequestBody @Valid MemberSaveRequestDto requestDto) {
        memberService.memberSave(requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.MEMBER_SAVE_SUCCESS));
    }

    // 회원 전체 조회
    @GetMapping
    public ResponseEntity<ApiResTemplate<MemberListResponseDto>> getAllMembers() {
        MemberListResponseDto responseDto = memberService.memberFindAll();
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, responseDto));
    }

    // 회원 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResTemplate<MemberInfoResponseDto>> getMember(@PathVariable Long memberId) {
        MemberInfoResponseDto responseDto = memberService.memberFindOne(memberId);
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, responseDto));
    }

    // 회원 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResTemplate<Void>> updateMember(
            @PathVariable Long memberId,
            @RequestBody @Valid MemberUpdateRequestDto requestDto) {
        memberService.memberUpdate(memberId, requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.MEMBER_UPDATE_SUCCESS));
    }

    // 회원 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResTemplate<Void>> deleteMember(@PathVariable Long memberId) {
        memberService.memberDelete(memberId);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.MEMBER_DELETE_SUCCESS));
    }
}
