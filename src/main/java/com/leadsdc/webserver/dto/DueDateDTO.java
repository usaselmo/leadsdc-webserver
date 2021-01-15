package com.leadsdc.webserver.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.leadsdc.webserver.service.Converter;

import lombok.Getter;

@Getter
public class DueDateDTO {
	public static final String DAYS = "%s days";
	public static final String DAYS_HOURS = "%sd %sh";
	public static final String HOURS_MINUTES = "%s:%s h";

	private final String date;
	private final String timeLeft;
	private final boolean isPastDue;

	public DueDateDTO(final LocalDateTime date) {
		this.date = date != null ? Converter.toString(date) : "";
		this.timeLeft = calculateTimeLeft(date);
		this.isPastDue = date != null ? date.toLocalDate().isBefore(LocalDate.now()) : false;
	}
	
	public DueDateDTO(final String date) {
		this(Converter.toLocalDateTime(date));
	}

	public LocalDateTime toLocalDateTime() {
		return Converter.toLocalDateTime(this.date);
	}

	private String calculateTimeLeft(final LocalDateTime date) {
		if (date == null)
			return "";
		LocalDateTime now = LocalDateTime.now();
		long days = Period.between(now.toLocalDate(), date.toLocalDate()).getDays();

		if (days >= 2) {
			return String.format(DAYS, days); // Dois dias ou mais
		} else {
			long hours = Duration.between(now, date).toHours();
			long minutes = Duration.between(now, date).toMinutes() - (60 * hours);
			if (hours >= 24) {
				days = Math.abs(hours/24);
				hours = hours - (days*24);
				if(hours==0)
					return String.format(DAYS, days); // Um dia alguns minutos
				else
					return String.format(DAYS_HOURS, days, hours); // Um dia e algumas horas
			}else 
				return String.format(HOURS_MINUTES, hours, minutes); // Algumas horas e minutos
			}
		}

}
