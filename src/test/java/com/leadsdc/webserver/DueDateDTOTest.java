package com.leadsdc.webserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.leadsdc.webserver.dto.DueDateDTO;

public class DueDateDTOTest {
	
	private static final String DATE = "11/27/19 02:36 PM";

	@Test
	public void future_test01() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusDays(2);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("2 days")).isTrue();
	}

	@Test
	public void future_test02() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusDays(3);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("3 days")).isTrue();
	}

	@Test
	public void future_test03() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusDays(1).plusMinutes(10);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("1 days")).isTrue();
	}

	@Test
	public void future_test03A() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusDays(1).plusMinutes(62);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("1d 1h")).isTrue();
	}

	@Test
	public void future_test04() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusHours(5).plusMinutes(36);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains(5+":"+3)).isTrue();
	}

	@Test
	public void future_test05() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusHours(4).plusMinutes(21);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains(4+":"+2)).isTrue();
	}

	@Test
	public void future_test06() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusHours(6).plusMinutes(42);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("6:4")).isTrue();
	}

	@Test
	public void future_test06A() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusHours(10).plusMinutes(42);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("10:4")).isTrue();
	}

	@Test
	public void future_test07() throws Exception {
		LocalDateTime future = LocalDateTime.now().plusDays(1).plusMinutes(61);
		DueDateDTO dueDate = new DueDateDTO(future);
		assertThat(dueDate.getTimeLeft().contains("1d 1h")).isTrue();
	}

	@Test
	public void future_test08() throws Exception {
		assertThat(DATE).isEqualTo(new DueDateDTO(DATE).getDate());
	}
	
	@Test
	public void reverse_test09() throws Exception {
		LocalDateTime first = LocalDateTime.now();
		LocalDateTime second = new DueDateDTO( new DueDateDTO(first).getDate() ).toLocalDateTime() ;
		assertThat(first.toString().contains(second.toString())).isTrue();
	}
	
	@Test
	public void reverse_test10() throws Exception {
		LocalDateTime first = LocalDateTime.now();
		LocalDateTime second = new DueDateDTO( new DueDateDTO(first).toLocalDateTime() ).toLocalDateTime() ;
		assertThat(first.toString().contains(second.toString())).isTrue();
	}
	
	@Test
	public void reverse_test11() throws Exception {
		LocalDateTime first = new DueDateDTO(DATE).toLocalDateTime();
		LocalDateTime second = new DueDateDTO( new DueDateDTO(first).toLocalDateTime() ).toLocalDateTime() ;
		assertThat(first.toString().contains(second.toString())).isTrue();
	}
	
	@Test
	public void reverse_test12() throws Exception {
		LocalDateTime first = new DueDateDTO(DATE).toLocalDateTime();
		assertThat( new DueDateDTO(first).getDate().contains(DATE)).isTrue();
	}
	
	@Test
	public void reverse_test13() throws Exception {
		LocalDateTime first = new DueDateDTO(DATE).toLocalDateTime();
		LocalDateTime second = new DueDateDTO( new DueDateDTO(first).toLocalDateTime() ).toLocalDateTime() ;
		assertThat( new DueDateDTO(second).getDate().contains(DATE)).isTrue();
	}

}
