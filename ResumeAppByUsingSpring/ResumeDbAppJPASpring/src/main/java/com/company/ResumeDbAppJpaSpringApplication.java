package com.company;

import com.company.dao.impl.UserRepository;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {

//	@Autowired
//	@Qualifier("userDao1")
//	private UserDaoInter userDao;
//
//	public static void main(String[] args) {
//		SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
//		System.out.println("I am working :)");
//	}
//
//	@Bean
//	public CommandLineRunner run(){
//		CommandLineRunner clr=new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				List<User> list=userDao.getAll(null,null,null);
//
//				User u=list.get(0);
//				u.setName(u.getName().toLowerCase());
//				userDao.updateUser(u);
//			}
//		};
//		return clr;
//	}


//	@Autowired
//	UserRepository repo;
//
//	@Autowired
//	UserServiceInter userService;

	public static void main(String[] args) {
		SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run() {
//		CommandLineRunner clr = new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				for(int i=0;i<10;i++){
//					userService.getAll(null,null,null);
//				}
//			}
//		};
//		return clr;
//
//	}
}
