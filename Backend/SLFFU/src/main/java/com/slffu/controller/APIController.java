package com.slffu.controller;

import com.slffu.services.LeagueDataHandlerI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

    @Autowired
    private LeagueDataHandlerI leagueDataHandlerService;

    @RequestMapping(value = "/getLeagueData/{leagueCode}")
    @ResponseBody
    public String getLeagueData(@PathVariable long leagueCode) {
        return leagueDataHandlerService.getDataForLeagues(leagueCode);
    }

    @RequestMapping(value = "")
    @ResponseBody
    public String index() {
        return "Hello world";
    }
}
