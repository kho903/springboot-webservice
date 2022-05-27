package com.jikim.webservice.springboot.web;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void main_page() {
	    // when
		String body = this.restTemplate.getForObject("/", String.class);

		// then
		assertThat(body).contains("Spring Boot Web Service");
	}
}