package com.ametov.postgres_test;

import com.ametov.postgres_test.user.entity.UserEntity;
import com.ametov.postgres_test.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostgresTestApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void repositoryTest(){
		UserEntity user = UserEntity.builder()
				.firstName("a")
				.lastName("b")
				.build();
		userRepository.save(user);

		UserEntity check = userRepository.findById(user.getId()).get();

		assert check.getId().equals(user.getId());
	}

}
