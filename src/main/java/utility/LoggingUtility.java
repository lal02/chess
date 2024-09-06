package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingUtility  {

    private static Logger logger = Logger.getLogger(LoggingUtility.class.getName());
    private static final Path directory = Paths.get(System.getProperty("user.home"),"Documents","ChessApplication","logs");
    private static final Path settingsFilePath = directory.resolve(DateUtility.getTimeStamp() + "_log.txt");

    static{
        try {
            //create the logs directory
            File settingsFile = settingsFilePath.toFile();
            File directory = settingsFile.getParentFile();
            if(!directory.exists()){
                directory.mkdir();
            }
            //configure the logger
            logger.setLevel(Level.INFO);
            FileHandler handler =  new FileHandler(settingsFilePath.toString());
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}



