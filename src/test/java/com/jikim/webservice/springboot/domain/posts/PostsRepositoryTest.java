package com.jikim.webservice.springboot.domain.posts;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest extends TestCase {

	@Autowired
	PostsRepository postsRepository;

	@After
	public void cleanUp() {
		postsRepository.deleteAll();
	}

	@Test
	public void 게시글저장_불러오기() {
		// given
		String title = "test post title";
		String content = "test post content";

		postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.author("jikim")
				.build());
		// when
		List<Posts> postsList = postsRepository.findAll();

		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);

	}

	@Test
	public void BaseTimeEntity_등록() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
			.title("title")
			.content("content")
			.author("author")
			.build());

		// when
		List<Posts> postsList = postsRepository.findAll();

		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);
	}
}