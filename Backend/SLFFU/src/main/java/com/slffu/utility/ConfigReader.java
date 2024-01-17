package com.slffu.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.slffu.dao.ConfigDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/*
* Reading the config.yaml file
*/
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger();

    public static ConfigDAO readConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(new File(Constants.CONFIG_YAML_PATH), ConfigDAO.class);
        } catch (Exception e) {
            logger.error("Cannot parse the configuration.", e);
        }
        return null;
    }

}
