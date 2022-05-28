package com.jikim.webservice.springboot.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.jikim.webservice.springboot.config.auth.SecurityConfig;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
			excludeFilters = {
			@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
				classes = SecurityConfig.class)
			})
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(roles = "USER")
	public void hello() throws Exception {
		String hello = "hello";
		mvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(hello));
	}

	@Test
	@WithMockUser(roles = "USER")
	public void helloDto_return() throws Exception {
		String name = "hello";
		int amount = 1000;

		mvc.perform(
			get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount))
		)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(name)))
			.andExpect(jsonPath("$.amount", is(amount)));
	}
}