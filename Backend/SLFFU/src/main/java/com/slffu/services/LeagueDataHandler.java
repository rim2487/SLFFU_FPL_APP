package com.slffu.services;

import com.slffu.dao.ConfigDAO;
import com.slffu.utility.ConfigReader;
import com.slffu.utility.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class LeagueDataHandler implements LeagueDataHandlerI {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public ResponseEntity<Long> getLeagueCode(String leagueType) {
        ConfigDAO configDAO = ConfigReader.readConfig();
        try {
            if (configDAO != null) {
                return switch (leagueType) {
                    case Constants.PFL -> ResponseEntity.ok(configDAO.getLeagueCodes().getPfl());
                    case Constants.CFL -> ResponseEntity.ok(configDAO.getLeagueCodes().getCfl());
                    case Constants.FLO -> ResponseEntity.ok(configDAO.getLeagueCodes().getFlo());
                    default -> {
                        logger.error("League type does not match!");
                        yield ResponseEntity.ok(Constants.LONG_ZERO);
                    }
                };
            } else {
                logger.error("ConfigDAO is null!");
                return ResponseEntity.ok(Constants.LONG_ZERO);
            }
        } catch (Exception e) {
            logger.error("Error fetching league data: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<String> getDataForLeagues(long leagueCode) {
        JSONArray leagueData;
        try {
            String requestUrl = Constants.FANTASY_API_REQUEST_URL + leagueCode + "/" + Constants.STANDINGS + "/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(requestUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            leagueData = new JSONObject(response.body()).getJSONObject(Constants.STANDINGS).getJSONArray(Constants.RESULTS);
            return ResponseEntity.ok(leagueData.toString());
        } catch (Exception e) {
            logger.error("Cannot get data for leagues. league code : " + leagueCode, e);
            return ResponseEntity.badRequest().build();
        }
    }

}
