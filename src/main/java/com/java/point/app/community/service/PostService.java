package com.java.point.app.community.service;

import com.java.point.app.community.dto.PostSaveRequest;
import com.java.point.app.community.entity.Post;
import com.java.point.app.community.repository.PostRepository;
import com.java.point.app.member.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class PostService {
    private final PostRepository postRepository;

    /**
     * 커뮤니티 게시글 저장
     * @param requestDto 사용자가 입력한 데이터 (제목, 내용 등)
     * @param author 현재 로그인한 사용자 엔티티
     * @return 저장된 게시글의 ID
     */
    @Transactional // 쓰기 작업이므로 별도로 Transactional 추가
    public Long save(PostSaveRequest requestDto, User author) {
        // record 내부의 toEntity 메서드를 호출하여 변환과 작성을 동시에 처리
        return postRepository.save(requestDto.toEntity(author)).getId();
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
