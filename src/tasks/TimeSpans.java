package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeSpans {

	public TimeSpans() {
		
	}
	/* Pour verifier si la date de la derni�re pointage est la m�me que la date de pointage actuel*/
	public boolean thisDateIsNow(String date) {

		LocalDate daty = LocalDate.parse(date);
		
		LocalDate nowDate = LocalDate.now();
		
		
		return daty.isEqual(nowDate);
	}
	
	public String plusTime(String hour,long adiny) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss",Locale.US);
		LocalTime time = LocalTime.parse(hour,formatter);
		
		LocalTime time2 = time.plusHours(adiny);
		time2.format(formatter);
		
		return time2.getSecond() == 0 ? time2.toString()+":00" : time2.toString();
	}
	public String minusTime(String hour,long adiny) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss",Locale.US);
		LocalTime time = LocalTime.parse(hour,formatter);
		
		LocalTime time2 = time.minusHours(adiny);
		time2.format(formatter);
		
		return time2.getSecond() == 0 ? time2.toString()+":00" : time2.toString();
	}
	
	public boolean inBetween(String start,String stop,LocalTime actualTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss",Locale.US);
		LocalTime startTime = LocalTime.parse(start,formatter);
		LocalTime stopTime = LocalTime.parse(stop,formatter);
		actualTime.format(formatter);

		
		boolean isInBetween = false;
		
		if(stopTime.isAfter(startTime)) {
			if(startTime.isBefore(actualTime) && stopTime.isAfter(actualTime)) {
				isInBetween = true;
			}
		}else if(actualTime.isBefore(stopTime)) {
			isInBetween = true;
		}
		
		return isInBetween;
	}
	
	public boolean BeforeTime(LocalTime actualTime,String debut) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss",Locale.US);
		LocalTime debutTime = LocalTime.parse(debut,formatter);
		actualTime.format(formatter);
		
		boolean isBefore = false;
		if(actualTime.isBefore(debutTime)) {
			isBefore = true;
		}
		return isBefore;
	}
}
