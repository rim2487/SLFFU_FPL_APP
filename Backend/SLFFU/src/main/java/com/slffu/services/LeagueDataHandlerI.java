package com.slffu.services;

import org.springframework.http.ResponseEntity;

public interface LeagueDataHandlerI {

    ResponseEntity<Long> getLeagueCode(String leagueType);
    ResponseEntity<String> getDataForLeagues(long leagueCode);

}
