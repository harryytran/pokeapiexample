import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

interface Pokemon {
    String getName();
    List<String> getTypes();
    int getHP();
    int getAttack();
    int getDefense();
}

class PokemonImpl implements Pokemon {
    private String name;
    private List<String> types;
    private int hp;
    private int attack;
    private int defense;

    public PokemonImpl(String name, List<String> types, int hp, int attack, int defense) {
        this.name = name;
        this.types = types;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getTypes() {
        return types;
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }
}

class PokemonTeamBuilder {
    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon fetchPokemonData(String name) throws Exception {
        String apiUrl = API_BASE_URL + name.toLowerCase();
        JSONObject pokemonData = fetchJsonFromApi(apiUrl);

        List<String> types = new ArrayList<>();
        for (int i = 0; i < pokemonData.getJSONArray("types").length(); i++) {
            types.add(pokemonData.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name"));
        }

        JSONObject stats = pokemonData.getJSONArray("stats").getJSONObject(0);
        int hp = stats.getInt("base_stat");
        int attack = pokemonData.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
        int defense = pokemonData.getJSONArray("stats").getJSONObject(2).getInt("base_stat");

        return new PokemonImpl(name, types, hp, attack, defense);
    }

    private JSONObject fetchJsonFromApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString());
    }
}

public class PokemonTeamBuilderApp {
    public static void main(String[] args) {
        PokemonTeamBuilder teamBuilder = new PokemonTeamBuilder();
        List<Pokemon> team = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (team.size() < 6) {
                System.out.print("Enter a Pokémon name (or 'done' to finish): ");
                String pokemonName = reader.readLine();

                if (pokemonName.equalsIgnoreCase("done")) {
                    break;
                }

                try {
                    Pokemon pokemon = teamBuilder.fetchPokemonData(pokemonName);
                    team.add(pokemon);
                    System.out.println("Added " + pokemon.getName() + " to your team!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + ". Please try again.");
                }
            }

            System.out.println("\nYour Pokémon Team:");
            for (Pokemon pokemon : team) {
                System.out.println("Name: " + pokemon.getName());
                System.out.println("Types: " + String.join(", ", pokemon.getTypes()));
                System.out.println("HP: " + pokemon.getHP());
                System.out.println("Attack: " + pokemon.getAttack());
                System.out.println("Defense: " + pokemon.getDefense());
                System.out.println();
            }

            int totalHP = team.stream().mapToInt(Pokemon::getHP).sum();
            int totalAttack = team.stream().mapToInt(Pokemon::getAttack).sum();
            int totalDefense = team.stream().mapToInt(Pokemon::getDefense).sum();

            System.out.println("Team Stats:");
            System.out.println("Total HP: " + totalHP);
            System.out.println("Total Attack: " + totalAttack);
            System.out.println("Total Defense: " + totalDefense);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
