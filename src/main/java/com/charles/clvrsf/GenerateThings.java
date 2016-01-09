package com.charles.clvrsf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GenerateThings {
	public static void main(String[] args) throws IOException, JSONException {
		
//		String s = FileUtils.readFileToString(new File("src/main/java/com/charles/clvrsf/hardwareModel.json"));
//		JSONArray ja = new JSONArray(s);
//		List<JSONObject> slicestorList = new ArrayList<JSONObject>();
//		
//		for(int i=0; i<ja.length(); i++){
//			JSONObject obj = ja.getJSONObject(i);
//			try{
//				if(obj.getLong("slicestorSizeUpperLimit") > -1){
////					slicestorList.add(obj);
//					slicestorEntry(obj);
////					break;
//				}
//			}
//			catch(Exception e){
//			}
//		}
//		
//		File outFile = new File("fakeDevice.sql");
//		
//		FileUtils.writeLines(outFile, qs);
		for(int i=1; i<10; i++){
			System.out.println(getScsiName(i));
		}
	}
	
	private static Integer deviceId = 1;
	private static int hpId = 1;
	private static String ipPrefix = "1.1.1.";
	private static String fingerprintPrefix = "b5:a0:d8:4f:f0:bd:1c:bb:d1:09:26:7a:c1:7f:31:19:2b:d2:fa:b";
	
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	private static void slicestorEntry(JSONObject obj) throws JSONException{
		String code = obj.getString("code");
		String modelName = obj.getString("modelName");
		Long driveCount = obj.getLong("slicestorSizeUpperLimit");
		
		createHP("\""+code+"\"", "\""+modelName+"\"", driveCount.intValue());
	}
	private static List<String> qs = new ArrayList<String>();
	
	private static void createDevice(String modelCode, String modelName){
		String creationDate = "\"11/11/2015\"";
		String hostname = modelName;
		String ip = "\"" + ipPrefix + deviceId.toString() + "\"";
		String uuid = "\"" + UUID.randomUUID().toString() + "\"";
		String deviceType = "\"slicestor\"";
		Integer dsnetId = 1;
		Integer siteId = null;
		String approvedThroughDate = "\"2015-11-11 11:23:52\"";
		Integer hwpId = deviceId;
		String fprint = "\"" + fingerprintPrefix + deviceId + "\"";
		
		String q = "insert into device(id, creation_date, hostname, uuid, device_type, dsnet_id, site_id, approved_through_date, hardware_profile_id, public_key_fingerprint)"
						+ " values ("
						+ deviceId + "," + creationDate + "," + hostname + "," + uuid + "," + deviceType + "," + dsnetId + "," + siteId + "," + approvedThroughDate + "," + null + "," + fprint
						+ ");";
		System.out.println(q);
		qs.add(q);
		
//		createHP(deviceId, modelCode, modelName, driveCount);
		
	}
	private static void createHP(String modelCode, String modelName, Integer driveCount){
		//Hardware Profile
		Long creationDate = 1447187574944L;
		String version = "\"3.7.0.58\"";
		String serial = "\"258113122061\"";
		String biosDate = "\"11/11/2015\"";
		String biosVersion = "\"1.0b-1\"";
		String firmware = "\"3.21 [0x6, 0x0, 0x0, 0x0]\"";
		Long storageSize = 104193095770112L;
		String chassisId = null;
		Integer nodeCount;
		Integer nodeLocation;
		String osDriveName = "\"sda\"";
		
		String q = "insert into hardware_profile (id, creation_date, version, model, model_code, device_id, serial, storage_size, bios_date, bios_version, bmc_firmware, os_drive_name)"
						+ " values ("
							+ hpId + ", " + creationDate + "," + version + "," + modelName + "," + modelCode + "," + deviceId + "," + serial + "," + storageSize + "," + biosDate + ","
							+ biosVersion + "," + firmware + "," + osDriveName + ");";
		
		
		
		createDevice(modelCode, modelName);
		
		qs.add(q);
		
		createHPDs(hpId, driveCount);
		
		updateDevice(deviceId);
		
		hpId++;
		deviceId = hpId;
	}
	private static void updateDevice(int id){
		String q = "update device set hardware_profile_id="+id+" where id="+id+";";
		qs.add(q);
	}
	
	private static int hpdId = 1;
	private static void createHPDs(int hpId, int count){
		for(int i=0; i<count; i++){
			createSingleHPD(hpId, i+1);
		}
	}
	
	private static void createSingleHPD(int hpId, int diskIdx){
		Long size = 3000592982016L;
		String name = "\"" + getScsiName(diskIdx) + "\"";
		
		String q = "insert into hardware_profile_disk (id, name, size, hardware_profile_id) values ("
					+ hpdId + "," + name + "," + size + "," + hpId + ");";
		
		System.out.println(q);
		qs.add(q);
		
		createHPPD(hpdId, diskIdx);
		hpdId++;
	}
	private static int hppdId = 1;
	private static void createHPPD(int hpdId, int diskIdx){
		//HardwareProfilePhysicalDisks
		String uuid = "\"" + UUID.randomUUID().toString() + "\"";
		Long seq = 0L;
		String model = "\"ST1000NM0011\"";
		String pdFirmware = "\"SN02\"";
		String serial = "\"SN02\"";
		Integer bay;
		
		String q = "insert into hardware_profile_physical_disk (id, uuid, hardware_profile_disk_id, seq, model, firmware, serial, bay) "
						+ " values ("
						+ hppdId + "," + uuid + "," + hpdId + "," + seq + "," + model + "," + pdFirmware + "," + serial + "," + (diskIdx-1)
						+ ");";
		
		qs.add(q);
		
		hppdId++;
	}
	
	private static String getScsiName(int i){
		int len = alphabet.length();
		
		int mod = i % len;
		double quo = Math.floor(i/len);
		int quoInt = (int) quo;

		String name = "sd" + (quoInt > 0 ? alphabet.charAt(quoInt-1) : "") + alphabet.charAt(mod);
		return name;
	}
	
}
