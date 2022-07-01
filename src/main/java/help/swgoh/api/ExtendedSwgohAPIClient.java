package help.swgoh.api;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import help.swgoh.api.models.event.Events;
import help.swgoh.api.models.guild.Guild;
import help.swgoh.api.models.player.Player;

import java.io.StringReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExtendedSwgohAPIClient extends SwgohAPIClient implements ExtendedSwgohAPI {

    ExtendedSwgohAPIClient(SwgohAPISettings settings) {
        super(settings);
    }

    public List<Player> getFullPlayers(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {

        String responseString = super.getPlayers(allyCodes, language, filter).get();


        return List.of(new Gson().fromJson(initReader(responseString), Player[].class));
    }


    @Override
    public List<Guild> getFullGuild(int allyCode, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {

        String responseString = super.getGuild(allyCode, language, filter).get();

        return List.of(new Gson().fromJson(initReader(responseString), Guild[].class));
    }

    @Override
    public List<Guild> getFullLargeGuild(int allyCode, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {

        String responseString = super.getLargeGuild(allyCode, language, filter).get();

        return List.of(new Gson().fromJson(initReader(responseString), Guild[].class));
    }

    // TODO: needs fixing
    /*
    @Override
    public List<Roster> getFullRosters(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {

        String responseString = super.getRosters(allyCodes, language, filter).get();

        return List.of(new Gson().fromJson(initReader(responseString), Roster[].class));
    }

     */

    @Override
    public Events getFullEvents(Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {

        String responseString = super.getEvents(language, filter).get();

        return new Gson().fromJson(initReader(responseString), Events.class);
    }

    private JsonReader initReader(String input) {

        JsonReader jsonReader = new JsonReader(new StringReader(input));
        jsonReader.setLenient(true);

        return jsonReader;
    }
}
