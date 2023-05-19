package br.com.movieflix.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public static LocalDateTime dataStringToClass(String data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
		return LocalDateTime.parse(data + " 00:00:00", formato);
	}
	
	public static LocalDateTime dataSessaoToClass(String data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
		return LocalDateTime.parse(data, formato);
	}
}
