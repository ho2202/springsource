package com.example.movie.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString(exclude = "movieImages")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    @ElementCollection
    @Builder.Default
    private List<MovieImage> movieImages = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void addImage(MovieImage movieImage) {
        movieImage.setOrd(this.movieImages.size());
        movieImages.add(movieImage);
    }

    public void clearList() {
        this.movieImages.clear();
    }
}
