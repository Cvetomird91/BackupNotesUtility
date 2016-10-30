import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BackupNotes {
	
	//private String configFilePath;
	
	//http://stackoverflow.com/questions/19674951/reading-ini-file-in-java
	//properties syntax: https://docs.oracle.com/cd/E23095_01/Platform.93/ATGProgGuide/html/s0204propertiesfileformat01.html
	
	//java.util.prefs.Preferences;
	//new Properties properties = rowProperties.load(new FileInputStream());
	
	//http://stackoverflow.com/questions/367706/how-to-parse-command-line-arguments-in-java
	
	private String BackupsDir;
	private String pCloudDir;
	private String LocalDir;

	public static void main(String[] args) {

		//setConfig("config.ini");
		
		BackupNotes backup = new BackupNotes();
		
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream("src/paths.properties"));
		} catch (IOException e){
			System.out.println(e);
		}

		backup.setConfig("src/paths.properties");		
		//backup.getAvailableBackups(backup);
		
	}
	
	private void setConfig(String configPath) {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream("src/paths.properties"));
		} catch (IOException e) {
			System.out.println(e);
		}
		
		this.BackupsDir = properties.getProperty("backups_dir");
		this.pCloudDir = properties.getProperty("pcloud_dir");
		this.LocalDir = properties.getProperty("local_dir");

	}
	
	private void getAvailableBackups(BackupNotes backup) {
		File backupsDir = new File(backup.BackupsDir);
		
		try {
			backupsDir.exists();
			backupsDir.isDirectory();
			FileInputStream stream = new FileInputStream(backupsDir);
		} catch (FileNotFoundException f) {
			System.out.println(f);
		}

		File[] availableBackups = backupsDir.listFiles();
		
		for (File f : availableBackups) {
			System.out.println(f.getName());
		}
	}
	
	private String generateDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}