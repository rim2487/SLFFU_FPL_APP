package com.slffu.dao;


import org.springframework.stereotype.Component;

@Component
public class ConfigDAO {

    private LeagueCodes leagueCodes;
    private String exampleString;
    private String secondString;


    public LeagueCodes getLeagueCodes() {
        return leagueCodes;
    }

    public void setLeagueCodes(LeagueCodes leagueCodes) {
        this.leagueCodes = leagueCodes;
    }

    public String getExampleString() {
        return exampleString;
    }

    public void setExampleString(String exampleString) {
        this.exampleString = exampleString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    @Override
    public String toString() {
        return "ConfigDAO{" +
                "leagueCodes=" + leagueCodes +
                ", exampleString='" + exampleString + '\'' +
                ", secondString='" + secondString + '\'' +
                '}';
    }
}
