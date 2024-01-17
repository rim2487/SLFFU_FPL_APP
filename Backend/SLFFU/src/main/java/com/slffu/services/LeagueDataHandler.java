package com.slffu.services;

import com.slffu.dao.ConfigDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.slffu.utility.ConfigReader;
import com.slffu.utility.Constants;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class LeagueDataHandler implements LeagueDataHandlerI {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public long getLeagueCode(String leagueType) {
        long leagueCode;
        ConfigDAO configDAO = ConfigReader.readConfig();
        try {
            switch (leagueType) {
                case Constants.PFL:
                    return configDAO.getLeagueCodes().getPfl();
                case Constants.CFL:
                    return configDAO.getLeagueCodes().getCfl();
                case Constants.FLO:
                    return configDAO.getLeagueCodes().getFlo();
                default:
                    logger.error("League type does not match!");
            }
        } catch (Exception e) {
            logger.error("Error fetching league data: ", e);
        }
        return Constants.LONG_ZERO;
    }

    @Override
    public String getDataForLeagues(long leagueCode) {
        JSONArray leagueData;
        try {
            String requestUrl = "https://fantasy.premierleague.com/api/leagues-classic/" + leagueCode + "/standings/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(requestUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            leagueData = new JSONObject(response.body()).getJSONObject("standings").getJSONArray("results");
            return leagueData.toString();
        } catch (Exception e) {
            logger.error("Cannot get data for leagues. league code : " + leagueCode, e);
        }
        return Constants.EMPTY_STRING;
    }

}
