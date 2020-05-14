package com.HoJun.app;

import com.HoJun.app.entity.*;
import com.HoJun.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final PersonRepository personRepository;
	private final SocialMediaRepository socialMediaRepository;
	private final InterestsRepository interestsRepository;
	private final SkillRepository skillRepository;
	private final WorkRepository workRepository;

	public AppApplication(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository, WorkRepository workRepository) {
		this.personRepository = personRepository;
		this.socialMediaRepository = socialMediaRepository;
		this.interestsRepository = interestsRepository;
		this.skillRepository = skillRepository;
		this.workRepository = workRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("서호준", "백엔드", "hello@test.com", "010-1234-5678"));
		interestsRepository.save(new Interests("영화감상, 유튜브 시청, 싱글 콘솔게임"));
		socialMediaRepository.save(new SocialMedia("anonymous", "anonymous", "", ""));
		skillRepository.save(new Skill("C", 60));
		skillRepository.save(new Skill("Java", 30));
		skillRepository.save(new Skill("JavaScript", 10));
		workRepository.save(new Work("3학년", "경성대학교 소프트웨어 공학과", "3학년 1학기", "C언어 기반, TCP/IP 소켓 프로그래밍을 기반으로 제작한 다중 클라이언트 채팅 프로그램"));
	}
}
