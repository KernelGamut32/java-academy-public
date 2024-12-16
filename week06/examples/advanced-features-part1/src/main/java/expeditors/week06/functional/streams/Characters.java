package expeditors.week06.functional.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Characters {
    public static void main(String[] args) {
        List<StarWarsCharacter> characters = getDemoCharacters();

        System.out.println("Using streams (first pass)...");
        characters.stream()
                        .filter(c -> c.movieName().equals("Star Wars"))
//                      .peek(c -> System.out.println(c))
                        .forEach(c -> System.out.println(c));
        System.out.println();
        System.out.println("Using streams (second pass)...");
        characters.stream()
                        .filter(c -> c.movieName().contains("of the"))
                        .sorted(Comparator.comparing(StarWarsCharacter::movieName))
                        .forEach(System.out::println);
        System.out.println();
        System.out.println("Using streams (third pass)...");
        characters.stream()
                        .filter(c -> c.name().equals("Yoda"))
                        .distinct()
                        .sorted((c1, c2) -> c1.movieName().compareTo(c2.movieName()))
                        .forEach(System.out::println);
        System.out.println();
        System.out.println("Using streams (fourth pass)...");
        characters.stream()
                        .filter(c -> c.name().equals("Obi-Wan Kenobi"))
                        .distinct()
                        .forEach(System.out::println);
    }

    private static List<StarWarsCharacter> getDemoCharacters() {
        List<StarWarsCharacter> characters = new ArrayList<>();
        characters.add(new StarWarsCharacter("Star Wars", "Han Solo", "Space pirate"));
        characters.add(new StarWarsCharacter("Star Wars", "Luke Skywalker", "The greatest Jedi"));
        characters.add(new StarWarsCharacter("Phantom Menace", "Qui-Gon Jinn", "Sage Jedi"));
        characters.add(new StarWarsCharacter("The Last Jedi", "Yoda", "Great he is"));
        characters.add(new StarWarsCharacter("Empire Strikes Back", "Yoda", "Luke's trainer"));
        characters.add(new StarWarsCharacter("Empire Strikes Back", "Obi-Wan Kenobi", "Luke's phantom trainer"));
        characters.add(new StarWarsCharacter("Return of the Jedi", "Darth Vader", "Dark Sith"));
        characters.add(new StarWarsCharacter("Attack of the Clones", "Darth Vader", "AKA Anakin Skywalker"));
        characters.add(new StarWarsCharacter("Star Wars", "Princess Leia", "Luke Skywalker's sister"));
        characters.add(new StarWarsCharacter("Attack of the Clones", "Obi-Wan Kenobi", "Timeless Jedi"));
        characters.add(new StarWarsCharacter("Attack of the Clones", "Obi-Wan Kenobi", "Timeless Jedi"));
        return characters;
    }
}
