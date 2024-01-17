package com.slffu;

import com.slffu.dao.ConfigDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.slffu.utility.ConfigReader;

@SpringBootApplication
public class SLFFUMain {

    public static void main(String[] args) {
        SpringApplication.run(SLFFUMain.class, args);
        ConfigDAO configDAO = ConfigReader.readConfig();
    }

}
