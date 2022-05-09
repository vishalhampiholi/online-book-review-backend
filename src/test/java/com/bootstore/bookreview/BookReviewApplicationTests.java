package com.bootstore.bookreview;

import com.bootstore.bookreview.model.Users;
import com.bootstore.bookreview.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;




@SpringBootTest
class BookReviewApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUsersTest(){
		Users user = new Users();
		user.setEmail("ravikumar@gmail.com");
		user.setPassword("ravi2020");
		user.setUsername("ravi");
		user.setRole("User");

		Users savedUser = userRepo.save(user);

		Users existUser = entityManager.find(Users.class, savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());


	}


}
