package datagenerator;

import demo.request.FootballMatchEndRequestDto;
import demo.request.FootballMatchStartRequestDto;
import demo.request.FootballMatchUpdateScoreRequestDto;

import java.util.ArrayList;
import java.util.List;

public class FootballMatchRequestDtoDataGenerator {

    public static List<FootballMatchStartRequestDto> getMatchStartRequestDtos(){
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = new ArrayList<>();

        FootballMatchStartRequestDto footballMatchStartRequestDto1
                = new FootballMatchStartRequestDto("Mexico", "Canada");
        FootballMatchStartRequestDto footballMatchStartRequestDto2
                = new FootballMatchStartRequestDto("Spain", "Brazil");
        FootballMatchStartRequestDto footballMatchStartRequestDto3
                = new FootballMatchStartRequestDto("Germany", "France");
        FootballMatchStartRequestDto footballMatchStartRequestDto4
                = new FootballMatchStartRequestDto("Uruguay", "Italy");
        FootballMatchStartRequestDto footballMatchStartRequestDto5
                = new FootballMatchStartRequestDto("Argentina", "Australia");

        footballMatchStartRequestDtos.add(footballMatchStartRequestDto5);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto4);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto3);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto2);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto1);

        return footballMatchStartRequestDtos;
    }

    public static List<FootballMatchUpdateScoreRequestDto> getMatchUpdateScoreRequestDtos(){
        List<FootballMatchUpdateScoreRequestDto> footballMatchUpdateRequestDtos = new ArrayList<>();

        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto1
                = new FootballMatchUpdateScoreRequestDto("Mexico", "Canada", 0, 5);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto2
                = new FootballMatchUpdateScoreRequestDto("Spain", "Brazil", 10, 2);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto3
                = new FootballMatchUpdateScoreRequestDto("Germany", "France", 2, 2);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto4
                = new FootballMatchUpdateScoreRequestDto("Uruguay", "Italy", 6, 6);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto5
                = new FootballMatchUpdateScoreRequestDto("Argentina", "Australia", 3, 1);

        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto1);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto2);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto3);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto4);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto5);

        return footballMatchUpdateRequestDtos;
    }

    public static List<FootballMatchEndRequestDto> getMatchEndRequestDtos(){
        List<FootballMatchEndRequestDto> footballMatchEndRequestDtos = new ArrayList<>();

        FootballMatchEndRequestDto matchEndRequestDto1 = new FootballMatchEndRequestDto("Mexico", "Canada");
        FootballMatchEndRequestDto matchEndRequestDto2 = new FootballMatchEndRequestDto("Spain", "Brazil");
        FootballMatchEndRequestDto matchEndRequestDto3 = new FootballMatchEndRequestDto("Germany", "France");
        FootballMatchEndRequestDto matchEndRequestDto4 = new FootballMatchEndRequestDto("Uruguay", "Italy");
        FootballMatchEndRequestDto matchEndRequestDto5 = new FootballMatchEndRequestDto("Argentina", "Australia");

        footballMatchEndRequestDtos.add(matchEndRequestDto1);
        footballMatchEndRequestDtos.add(matchEndRequestDto2);
        footballMatchEndRequestDtos.add(matchEndRequestDto3);
        footballMatchEndRequestDtos.add(matchEndRequestDto4);
        footballMatchEndRequestDtos.add(matchEndRequestDto5);

        return footballMatchEndRequestDtos;
    }
}
