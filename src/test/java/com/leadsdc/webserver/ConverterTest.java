package com.leadsdc.webserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import com.leadsdc.webserver.service.Converter;

public class ConverterTest {

	private static final String _11_27_19_02_36_PM = "11/27/19 02:36 PM";

	@Test
	public void localDateTimeToStringTest01() throws Exception {
		LocalDateTime ldt = LocalDateTime.now();
		String res = Converter.toString(ldt);
		assertThat(res.contains(ldt.getMonthValue() + "/" + ldt.getDayOfMonth())).isTrue();
	}

	@Test
	public void stringToLocalDateTime_test00() throws Exception {
		LocalDateTime res = Converter.toLocalDateTime(_11_27_19_02_36_PM);
		assertThat(res.getDayOfMonth() == 27).isTrue();
	}

	@Test
	public void stringToLocalDateTime_test01() throws Exception {
		LocalDateTime res = Converter.toLocalDateTime(_11_27_19_02_36_PM);
		assertThat(res.getMonthValue() == 11).isTrue();
	}

	@Test
	public void stringToLocalDateTime_test02() throws Exception {
		LocalDateTime res = Converter.toLocalDateTime(_11_27_19_02_36_PM);
		assertThat(res.getYear() == 2019).isTrue();
	}

	@Test
	public void stringToLocalDateTime_test03() throws Exception {
		LocalDateTime res = Converter.toLocalDateTime(_11_27_19_02_36_PM);
		assertThat(res.getHour() == 14).isTrue();
	}

	@Test
	public void testName() throws Exception {
		assertThat(_11_27_19_02_36_PM).isEqualTo(Converter.toString(Converter.toLocalDateTime(_11_27_19_02_36_PM)));
	}

	@Test
	public void null_01() throws Exception {
		LocalDateTime res = Converter.toLocalDateTime("");
		assertThat(res).isNull();
	}

	@Test
	public void null_02() throws Exception {
		String res = Converter.toString(null);
		assertThat(StringUtils.hasText(res)).isFalse();
	}

}
