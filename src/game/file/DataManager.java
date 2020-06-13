package game.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import game.GameManager;
import game.PlayInfo;

public class DataManager {
	
	public static int MAX_COUNT = 10;
	private static final String FILENAME = "./data";
	private static final String FILE_SUFFIX = ".ser";

	private ArrayList<SaveData> dataList = new ArrayList<>();
	
	public DataManager() {
		
	}
		
	
	public ArrayList<SaveData> getDataList() {
		return dataList;
	}
	
	public void makeSaveFile(GameManager gm, PlayInfo info) {
		if(dataList.size() >= MAX_COUNT)
			return;
		
		SaveData data = new SaveData(); 
		data.setPlayInfo(gm, info);
		dataList.add(data);
		serialize(dataList.size() - 1);
		/*
		File file = new File(FILENAME + dataList.size() + FILE_SUFFIX);
		if(!file.exists()) {
			try {
				file.createNewFile();
				dataList.add(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/		
	}

	public void load() {
		while(true) {
			SaveData data = deserialize(FILENAME + dataList.size() + FILE_SUFFIX);
			
			if(data == null)
				break;
			
			dataList.add(data);
		}
	}

	public void serialize(int idx) {
		try { 
			FileOutputStream fos = new FileOutputStream(FILENAME + idx + FILE_SUFFIX);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos); 
			
			out.writeObject(dataList.get(idx));
			out.close(); 
		} 
		catch (Exception e) { e.printStackTrace(); }

	}
	
	private SaveData deserialize(String name) {
		SaveData data = null;
		
		try { 
			File file = new File(name);
			if(!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream(name); 
			BufferedInputStream bis = new BufferedInputStream(fis); 
			ObjectInputStream in = new ObjectInputStream(bis); 
			data = (SaveData)in.readObject();

			in.close();
		} 
		catch (EOFException e) { 
		}
		catch (Exception e) {
			e.printStackTrace(); 
			
		}
		return data;
	}
	
} 
