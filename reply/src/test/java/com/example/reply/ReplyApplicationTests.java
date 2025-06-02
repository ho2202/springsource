package com.example.reply;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.reply.entity.Member;
import com.example.reply.entity.Movie;
import com.example.reply.repository.MemberRepository;
import com.example.reply.repository.MovieRepository;

@SpringBootTest
class ReplyApplicationTests {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Test
	void contextLoads() {
		IntStream.rangeClosed(1, 20).forEach(i -> {

			Member me = Member.builder().build();
			memberRepository.save(me);
			Movie movie = Movie.builder().title("title").mno((long) (i)).build();
			movieRepository.save(movie);
		});
	}

}
