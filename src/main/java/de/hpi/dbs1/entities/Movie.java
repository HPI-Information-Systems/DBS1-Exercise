package de.hpi.dbs1.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Movie {

	public final @NotNull String tConst;

	public final @NotNull String title;

	/**
	 * must be null if it was NULL in the database
	 */
	public final Integer year;

	public final @NotNull Set<String> genres;

	/**
	 * list of actor names
	 */
	public final @NotNull List<String> actorNames = new ArrayList<>();

	public Movie(
		@NotNull String tConst, @NotNull String title, Integer year, @NotNull Set<String> genres
	) {
		this.tConst = Objects.requireNonNull(tConst);
		this.title = Objects.requireNonNull(title);
		this.year = year;
		this.genres = Objects.requireNonNull(genres);
	}

	@Override
	public String toString() {
		return "Movie{tConst='%s', title='%s', year=%d, genres=%s, actorNames=%s}"
			.formatted(tConst, title, year, genres, actorNames);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;

		final Movie movie = (Movie) o;

		if(!tConst.equals(movie.tConst))
			return false;
		if(!title.equals(movie.title))
			return false;
		if(!Objects.equals(year, movie.year))
			return false;
		return genres.equals(movie.genres);
	}

	@Override
	public int hashCode() {
		int result = tConst.hashCode();
		result = 31 * result + title.hashCode();
		result = 31 * result + (year != null ? year.hashCode() : 0);
		result = 31 * result + genres.hashCode();
		return result;
	}
}
