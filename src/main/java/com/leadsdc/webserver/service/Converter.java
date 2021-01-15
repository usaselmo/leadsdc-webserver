package com.leadsdc.webserver.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.exception.LeadsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Converter {

	private static final String D_MM_YY_HH_MM_A = "MM/d/yy hh:mm a";
	public static final String dd_MM_yyyy = "dd/MM/yyyy";
	public static final String MM_dd_yy = "MM/dd/yy";
	public static final String MM_dd_yy_hh_mm = "MM/dd/yy hh:mm";
	public static final String MM_dd_yyyy = "MM/dd/yyyy";
	public static final String MM_dd_yyyy_hh_mm = "MM/dd/yyyy hh:mm";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * Default pattern dd/MM/yyyy
	 */
	public static LocalDate stringToLocalDate(String dateToConvert) {
		return stringToLocalDate(dateToConvert, dd_MM_yyyy);
	}

	public static LocalDate stringToLocalDate(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(date, formatter);
	}

	public static LocalDate toLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date stringToDate(String dateToConvert, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateToConvert);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date) {
		try {
			return new SimpleDateFormat(MM_dd_yy_hh_mm).format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static String dateToString(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date convertToDate(String date) {
		if (!StringUtils.hasText(date))
			return null;
		return Converter.stringToDate(date, MM_dd_yyyy);
	}

	public static Date convertToDate(String date, String format) {
		if (!StringUtils.hasText(date))
			return null;
		return Converter.stringToDate(date, format);
	}

	public static LocalDate convertToLocalDate(String date) throws LeadsException {
		try {
			return Converter.stringToLocalDate(date, "MM/dd/yyyy");
		} catch (Exception e) {
			try {
				return Converter.stringToLocalDate(date, "MM/dd/yy");
			} catch (Exception e1) {
				try {
					return Converter.stringToLocalDate(date);
				} catch (Exception e2) {
					log.error("Could not convert this string to Date: " + date);
					throw new LeadsException("Could not convert this string to Date: " + date);
				}
			}
		}
	}
	
	public static String toString(LocalDateTime localDateTime) {
		if(localDateTime==null) return "";
		return DateTimeFormatter.ofPattern(D_MM_YY_HH_MM_A).format(localDateTime);
	}
	
	public static LocalDateTime toLocalDateTime (String string) {
		if(!StringUtils.hasText(string)) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(D_MM_YY_HH_MM_A);
	  return LocalDateTime.parse(string, formatter);
	}

}
