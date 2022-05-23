package com.jikim.webservice.springboot.web.dto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.Test;

public class HelloResponseDtoTest {

	@Test
	public void lombok_test() throws Exception {
	    // given
		String name = "test";
		int amount = 100;

	    // when
		HelloResponseDto dto = new HelloResponseDto(name, amount);

		// then
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}
}