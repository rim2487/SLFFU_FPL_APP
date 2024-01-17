package com.slffu.services;

public interface LeagueDataHandlerI {
    long getLeagueCode(String leagueType);
    String getDataForLeagues(long leagueCode);

}
