import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BackupNotes {

	/* 
	 * 1. Generate new directory name and path
	 * 2. Get a list of the current files in the notes_directory
	 * 3. Get available backups
	 * 4. check if a daily backup is performed, throw error if one is created
	 * 5. Force backup option
	 * 6. Parse command line options accordingly
	 * 7. Generate line count file name - ${LINE_COUNT_FILE}-${DATE}
	 * 8. Generate diff file
	 * 9. Generate line counts file name
	 */
	
	private String BackupsDir;
	private String pCloudDir;
	private String LocalDir;
	private String Properties = "src/paths.properties";

	public static void main(String[] args) {
		
		BackupNotes backup = new BackupNotes();
		
		Properties properties = new Properties();

		backup.setConfig(backup.Properties);		
		backup.getAvailableBackups(backup);

	}
	
	private void setConfig(String configPath) {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(this.Properties));
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
	
	private String generateFileName(String prefix) {
		return prefix + this.generateDateString();
	}
	
	private int getLineCount(String filepath) {
	/*
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			int lines = 0;
			while (reader.readline() != null) lines++;
			reader.close();
		} catch (IOException io) {
			System.out.println(io);
		}

		//return lines;
		 
	*/
		return 1;
	}
	
	private File[] getNotesFiles(String notesPath) {
		File folder = new File(notesPath);
		File[] listOfFiles = folder.listFiles();
		
		return listOfFiles;
	}
	
}