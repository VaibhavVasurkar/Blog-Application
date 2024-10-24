package com.vaibhavvasurkar.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaibhavvasurkar.blog.repositories.UserEntityRepository;

@SpringBootTest
class BlogAppBackendApplicationTests {
	
	@Autowired
	private UserEntityRepository userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		String className = userRepo.getClass().getName();
		String packName = userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packName);
	}

}
