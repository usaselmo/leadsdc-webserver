package com.leadsdc.webserver.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.model.Proposal;
import com.leadsdc.webserver.service.Converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProposalDTO {

	private final String id;
	private final String number;
	private final String total;
	private final String formattedTotal;
	private final String scopeOfWork;
	private final boolean callMissUtility;
	private final String paymentSchedule;
	private final String workWarranty;
	private final boolean emailed;
	private final String note;
	private final List<ItemDTO> items;
	private final String date;
	private final boolean changeorder;
	private final boolean accepted;
	
	public static final ProposalDTO of(Proposal proposal) {
		if(proposal == null) return null;
		return new ProposalDTO(String.valueOf(proposal.getId()), 
				proposal.getNumber()!=null ? String.valueOf(proposal.getNumber()) : "",
				proposal.getTotal()!=null ? proposal.getTotal().toString().replaceAll("$",	"") : "", 
				proposal.getTotal() != null ? NumberFormat.getCurrencyInstance().format(proposal.getTotal()) : "",
				proposal.getScopeOfWork(),
				proposal.isCallMissUtility(),
				proposal.getPaymentSchedule(),
				proposal.getWorkWarranty(),
				proposal.isEmailed(),
				proposal.getNote(),
				proposal.getItems()!=null ? proposal.getItems().stream().map(i->ItemDTO.of(i)).collect(Collectors.toList()) : Collections.emptyList(),
				proposal.getDate()!=null ? Converter.dateToString(proposal.getDate(), Converter.MM_dd_yyyy) : "",
				proposal.isChangeorder(),
				proposal.isAccepted());
	}
	
	public static final Proposal toProposal(ProposalDTO proposalDTO) {
		if(proposalDTO == null) return null;
		Proposal proposal = new Proposal();
		proposal.setId(!StringUtils.hasText(proposalDTO.getId())?null:Long.valueOf(proposalDTO.getId()));
		proposal.setCallMissUtility(proposalDTO.isCallMissUtility());
		proposal.setEmailed(proposalDTO.isEmailed());
		proposal.setNote(proposalDTO.getNote()); 
		proposal.setNumber(!StringUtils.hasText(proposalDTO.getNumber())?null:Long.valueOf(proposalDTO.getNumber()));
		proposal.setTotal(!StringUtils.hasText(proposalDTO.getTotal())?BigDecimal.ZERO:new BigDecimal(proposalDTO.getTotal().replace("$", "")));
		proposal.setPaymentSchedule(proposalDTO.getPaymentSchedule());
		proposal.setScopeOfWork(proposalDTO.getScopeOfWork());
		proposal.setTotal(!StringUtils.hasText(proposalDTO.getTotal())?BigDecimal.ZERO:new BigDecimal(proposalDTO.getTotal().replace("$", "").replace(",", "")));
		proposal.setWorkWarranty(proposalDTO.getWorkWarranty());
		proposal.setItems(proposalDTO.getItems().stream().map(i->ItemDTO.toItem(i)).collect(Collectors.toList()));
		proposal.setDate(Converter.stringToDate(proposalDTO.getDate(), Converter.MM_dd_yy));
		proposal.setChangeorder(proposalDTO.isChangeorder());
		proposal.setAccepted(proposalDTO.isAccepted());
		return proposal;
	}
	
}
