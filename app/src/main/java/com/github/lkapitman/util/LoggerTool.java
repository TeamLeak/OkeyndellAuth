package com.github.lkapitman.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.github.lkapitman.App;

public class LoggerTool {
    
    private final App instance;
    private static Logger logger;
    private final String name;

    public LoggerTool(App instance, String name) {
        this.instance = instance;
        this.name = name;
    }

    public void init() throws SecurityException, IOException {
        logger = Logger.getLogger(name);
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        FileHandler fh = new FileHandler(new File(instance.getDataFolder(), "latest").getAbsolutePath() + " " + format.format(Calendar.getInstance().getTime()) + ".log");
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }

    public void log(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warning(message);
    }

}
