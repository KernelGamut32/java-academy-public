package expeditors.week06.functional.streams;

import java.util.Objects;

public record StarWarsCharacter(String movieName, String name, String description) {
    @Override
    public String toString() {
        return String.format("%s: %s (%s)", movieName, name, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarWarsCharacter that = (StarWarsCharacter) o;
        return Objects.equals(movieName, that.movieName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, name, description);
    }
}
