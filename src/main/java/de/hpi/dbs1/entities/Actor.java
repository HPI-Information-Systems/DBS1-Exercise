package de.hpi.dbs1.entities;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Actor {

	public final @NotNull String nConst;

	public final @NotNull String name;

	/**
	 * list of movie original titles
	 */
	public final @NotNull List<String> playedIn = new ArrayList<>();

	/**
	 * map of costar name to number of shared movies with that costar
	 */
	public final @NotNull Map<String, Integer> costarNameToCount = new LinkedHashMap<>();

	public Actor(
		@NotNull String nConst,
		@NotNull String name
	) {
		this.nConst = Objects.requireNonNull(nConst);
		this.name = Objects.requireNonNull(name);
	}

	@Override
	public String toString() {
		return "Actor{nConst='%s', name='%s', playedIn=%s, costarNameToCount=%s}"
			.formatted(nConst, name, playedIn, costarNameToCount);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;

		final Actor actor = (Actor) o;

		if(!nConst.equals(actor.nConst))
			return false;
		return name.equals(actor.name);
	}

	@Override
	public int hashCode() {
		int result = nConst.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
