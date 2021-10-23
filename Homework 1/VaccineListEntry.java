package cs3220.model;

public class VaccineListEntry {
	private String name; 
	private int doseCount; 
	private int dayCount; 
	private int dosesReceived; 
	private int dosesLeft;
	
	public VaccineListEntry(String name, int doseCount, int dayCount, int dosesReceived, int dosesLeft) {
		this.name = name; 
		this.doseCount = doseCount; 
		this.dayCount = dayCount; 
		this.dosesReceived += dosesReceived; 
		this.dosesLeft += dosesLeft; 
	}
	public void addDoses(int dosesReceived, int dosesLeft) {
		this.dosesReceived += dosesReceived;
		this.dosesLeft += dosesLeft; 
	}
	
	public String getURL() {
		String url = name.trim().replaceAll("&", ""); 
		url.replaceAll("\\s", ""); 
		return url; 
	}
	public String getNameEdit() {
		String edit = name.trim().replaceAll("\\s", ""); 
		return edit; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDoseCount() {
		return doseCount;
	}
	public void setDoseCount(int doseCount) {
		this.doseCount = doseCount;
	}
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	public int getDosesReceived() {
		return dosesReceived;
	}
	public void setDosesReceived(int dosesReceived) {
		this.dosesReceived = dosesReceived;
	}
	public int getDosesLeft() {
		return dosesLeft;
	}
	public void setDosesLeft(int dosesLeft) {
		this.dosesLeft = dosesLeft;
	} 
	
	public void editEntry(String name, int doseCount, int dayCount) {
		this.name = name; 
		this.doseCount = doseCount;
		this.dayCount = dayCount; 
	}
	
	
}
