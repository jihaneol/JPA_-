package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> MemberV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public ResponseEntity<?> MemberV2() {
        List<Member> findMember = memberService.findMembers();
        List<MemberDto> collect = findMember.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);

    }


    @PostMapping("/api/v1/members")
    public CreateMemberRespons saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberRespons(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberRespons saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberRespons(id);
    }
    @PutMapping ("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class  CreateMemberRespons {
        private  Long id;

        public CreateMemberRespons(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }
}
