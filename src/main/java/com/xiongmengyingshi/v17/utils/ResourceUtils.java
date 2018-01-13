package com.xiongmengyingshi.v17.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
public class ResourceUtils {

    public static Logger logger = LogManager.getLogger(ResourceUtils.class);

    private final static String configfileName = "dev/config";

    public static String getBundleValue4String(String key) {
        return getBundleValue4String(key, "");
    }

    public static Long getBundleValue4Long(String key) {
        return getBundleValue4Long(key, 0l);
    }

    public static Double getBundleValue4Double(String key) {
        return getBundleValue4Double(key, 0d);
    }

    public static boolean getBundleValue4Boolean(String key) {
        return getBundleValue4Boolean(key, false);
    }

    public static String getBundleValue4String(String key, String defaultValue) {
        ResourceBundle resource = ResourceBundle.getBundle(configfileName);
        try {
            defaultValue = resource.getString(key);
        } catch (Exception e) {
            logger.info("getBundleValue exception" + key + " : " + defaultValue + " " + e.getMessage());
        }
        return defaultValue;
    }


    public static Long getBundleValue4Long(String key, Long defaultValue) {
        ResourceBundle resource = ResourceBundle.getBundle(configfileName);
        try {
            defaultValue = Long.valueOf(resource.getString(key));
        } catch (Exception e) {
            logger.info("getBundleValue exception" + key + " : " + defaultValue + " " + e.getMessage());
        }
        return defaultValue;
    }

    public static Double getBundleValue4Double(String key, Double defaultValue) {
        ResourceBundle resource = ResourceBundle.getBundle(configfileName);
        try {
            defaultValue = Double.valueOf(resource.getString(key));
        } catch (Exception e) {
            logger.info("getBundleValue exception" + key + " : " + defaultValue + " " + e.getMessage());
        }
        return defaultValue;
    }

    public static boolean getBundleValue4Boolean(String key, boolean defaultValue) {
        ResourceBundle resource = ResourceBundle.getBundle(configfileName);
        try {
            defaultValue = Boolean.valueOf(resource.getString(key));
        } catch (Exception e) {
            logger.info("getBundleValue exception" + key + " : " + defaultValue + " " + e.getMessage());
        }
        return defaultValue;
    }

}
