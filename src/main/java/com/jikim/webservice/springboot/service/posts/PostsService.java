package com.jikim.webservice.springboot.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikim.webservice.springboot.domain.posts.Posts;
import com.jikim.webservice.springboot.domain.posts.PostsRepository;
import com.jikim.webservice.springboot.web.dto.PostsListResponseDto;
import com.jikim.webservice.springboot.web.dto.PostsResponseDto;
import com.jikim.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.jikim.webservice.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostsService {

	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(
				"해당 게시글이 없습니다. id=" + id
			));

		return new PostsResponseDto(entity);
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(
				"해당 게시글이 없습니다. id=" + id
			));

		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllOByOrderByIdDesc().stream()
			.map(PostsListResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void deletePosts(Long id) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(
				"해당 게시글이 없습니다. id=" + id
			));

		postsRepository.delete(posts);
	}
}
