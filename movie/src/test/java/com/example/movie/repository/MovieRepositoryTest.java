package com.example.movie.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.movie.entity.Member;
import com.example.movie.entity.MemberRole;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.Review;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImageRepository movieImageRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testFindByMovie() {
        System.out.println(reviewRepository.findByMovie(Movie.builder().mno(2L).build()));
    }

    @Transactional
    @Test
    public void testFindByMovie2() {
        List<Review> list = reviewRepository.findByMovie(Movie.builder().mno(2L).build());

        for (Review review : list) {
            System.out.println(review);
            System.out.println(review.getMember().getEmail());
        }
    }

    @Test
    public void insertMovieTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie" + i).build();
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .ord(j)
                        .imgName("test" + j + ".jpg")
                        .movie(movie)
                        .build();

                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void memberInsertTest() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@mail.com")
                    .password(passwordEncoder.encode("1111"))
                    .memberRole(MemberRole.MEMBER)
                    .nickname("viewer" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void reviewInsertTest() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long count = (long) (Math.random() * 100) + 1;
            long id = i % 20 + 1;
            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("평가" + i)
                    .movie(Movie.builder().mno(count).build())
                    .member(Member.builder().mid(id).build())
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    public void listTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageable);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // [Movie(mno=92, title=Movie92), MovieImage(inum=282,
            // uuid=5073faef-c112-4bd1-ad09-fd58b0536d31, imgName=test0.jpg, path=null,
            // ord=0), 2, 4.0]
        }
    }

    @Test
    public void getMovieTest() {
        List<Object[]> list = movieImageRepository.getMovieRow(2L);
        // for (Object[] objects : list) {
        // System.out.println(Arrays.toString(objects));
        // }
        Movie movie = (Movie) list.get(0)[0];
        MovieImage movieImage = (MovieImage) list.get(0)[1];
        Long cnt = (Long) list.get(0)[2];
        Double avg = (Double) list.get(0)[3];
    }

    @Test
    public void test() {
    }

}
