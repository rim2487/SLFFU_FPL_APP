package com.slffu.dao;


import org.springframework.stereotype.Component;

@Component
public class ConfigDAO {

    private LeagueCodes leagueCodes;

    public LeagueCodes getLeagueCodes() {
        return leagueCodes;
    }

    public void setLeagueCodes(LeagueCodes leagueCodes) {
        this.leagueCodes = leagueCodes;
    }

}
