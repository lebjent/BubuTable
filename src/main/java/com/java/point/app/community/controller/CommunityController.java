package com.java.point.app.community.controller;

import com.java.point.app.community.dto.PostSaveRequest;
import com.java.point.app.community.entity.Post;
import com.java.point.app.community.service.PostService;
import com.java.point.app.member.dto.MemberContext;
import com.java.point.app.member.entity.User;
import com.java.point.app.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final PostService postService;
    private final UserRepository userRepository;

    /**
     * 글쓰기 화면 이동
     */
    @GetMapping("/write")
    public String writeForm() {
        return "community/post-write"; // templates/community/post-write.html 파일
    }

    /**
     * 글 등록 로직
     * @param requestDto : record로 만든 PostSaveRequest
     * @param memberContext : 현재 로그인한 유저의 세션 정보
     */
    @PostMapping("/write")
    public String save(@ModelAttribute PostSaveRequest requestDto,
                       @AuthenticationPrincipal MemberContext memberContext) {

        // 1. 세션의 username(보통 이메일)을 통해 실제 DB의 User 엔티티 조회
        User author = userRepository.findByEmail(memberContext.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("작성자 정보를 찾을 수 없습니다."));

        // 2. 서비스 호출하여 저장
        postService.save(requestDto, author);

        // 3. 완료 후 목록 페이지로 이동
        return "redirect:/community";
    }

    @GetMapping("/post-list")
    public String list(Model model,
                       @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);

        // 뷰에서 페이지네이션 계산을 쉽게 하기 위한 변수들
        int nowPage = posts.getPageable().getPageNumber() + 1; // 현재 페이지 (0부터 시작하므로 +1)
        int startPage = Math.max(nowPage - 4, 1); // 시작 페이지 번호
        int endPage = Math.min(nowPage + 5, posts.getTotalPages()); // 끝 페이지 번호

        model.addAttribute("posts", posts);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "community/post-list";
    }

}
