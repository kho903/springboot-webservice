package com.jikim.webservice.springboot.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

	List<Posts> findAllOByOrderByIdDesc();
}
