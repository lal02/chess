package settings;

import java.io.*;
import java.nio.file.Files;
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
                directory.mkdir();
            }
            if(!settingsFile.exists()){
                settingsFile.createNewFile();
            }
            FileReader fr = new FileReader(settingsFile);
            settings.load(fr);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //parse all the values to the fields
//        boolean sound = Boolean.parseBoolean(settings.getProperty("sound"));
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
        System.out.println("Saving Settings");
        try {
            OutputStream out = new FileOutputStream(settingsFilePath.toFile());
            settings.store(out,"Settings");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setSound(boolean status){
        settings.setProperty("sound", String.valueOf(status));
        System.out.println(settings.getProperty("sound"));
    }

    public boolean getSound(){
        return Boolean.parseBoolean(settings.getProperty("sound"));
    }
}
