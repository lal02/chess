package settings;

import utility.LoggingUtility;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
    private Properties settings;
    private static Settings instance = null;
    private static final Path directory = Paths.get(System.getProperty("user.home"),"Documents","ChessApplication");
    private static final Path settingsFilePath = directory.resolve("settings.properties");

    private Settings(){
        settings = new Properties();
        try {
            File settingsFile = settingsFilePath.toFile();
            File directory = settingsFile.getParentFile();
            if(!directory.exists()){
               LoggingUtility.getLogger().info("Directory created: "+directory.mkdir());
                LoggingUtility.getLogger().info("Created directory with path" + directory.getCanonicalPath());
            }
            if(!settingsFile.exists()){
               LoggingUtility.getLogger().info("file created:"+ settingsFile.createNewFile());
                LoggingUtility.getLogger().info("Created file with path" + settingsFile.getCanonicalPath());
            }
            FileReader fr = new FileReader(settingsFile);
            settings.load(fr);
            LoggingUtility.getLogger().info("Loaded Settings");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Singleton for Settings Object so only one Settings Object can be used at the same time.
     * Prevents issues where multiple instances try to edit / load the same settings file.
     * @return Singleton Settings Object
     */
    public static Settings getSettingsInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }


    /**
     * Saves the Property file. Called on Application Exit
     */
    public void saveSettings(){
        try {
            OutputStream out = new FileOutputStream(settingsFilePath.toFile());
            settings.store(out,"Settings");
            LoggingUtility.getLogger().info("Saved Settings");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setSound(boolean status){
        settings.setProperty("", String.valueOf(status));
        LoggingUtility.getLogger().info("Set sound to "+status);
    }

    public boolean getSound(){
        LoggingUtility.getLogger().info("Parsing sound property to "+settings.getProperty(""));
        return Boolean.parseBoolean(settings.getProperty(""));
    }

    public void setBackground(Background background){
        settings.setProperty("background",background.toString());
        LoggingUtility.getLogger().info("Set background to "+background);
    }

    public Background getBackground(){
        if(settings.getProperty("background")==null){
            LoggingUtility.getLogger().info("No background property found, falling back to default Background.LIGHTGREEN");
            setBackground(Background.LIGHTGREEN);
            return Background.LIGHTGREEN;
        }
        else{
            LoggingUtility.getLogger().info("Loading Background: "+settings.getProperty("background"));
            return Background.valueOf(settings.getProperty("background"));
        }
    }
}
