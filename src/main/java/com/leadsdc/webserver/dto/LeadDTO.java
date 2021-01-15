package com.leadsdc.webserver.dto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.exception.LeadsException;
import com.leadsdc.webserver.model.Lead;
import com.leadsdc.webserver.model.Proposal;
import com.leadsdc.webserver.service.Converter;
import com.leadsdc.webserver.type.Event;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LeadDTO {
	
	//SINGLE PROPERTIES
	private final String id;
	private final String date;
	private final String description;
	private final String notes;
	private final String title;
	private final String address;
	private final String event;
	private final String totalPrice;

	private final DueDateDTO visit;
	private final DueDateDTO dueDate;
	
	private final UserDTO estimator;
	private final CompanyDTO company;
	private final PersonDTO contact;
	private final PersonDTO client;
	
	private final List<ProposalDTO> proposals;
	private final List<MediaDTO> medias;
	private final List<InvitationDTO> invitations;
	
	public static final LeadDTO of(Lead lead) {
		if(lead==null)
			return null;
		return LeadDTO.builder()
				.id(String.valueOf(lead.getId()))
				.description(lead.getDescription())
				.notes(lead.getNotes())
				.client(PersonDTO.of(lead.getClient()))
				.proposals(lead.getProposals()==null?null:lead.getProposals().stream().map(p->ProposalDTO.of(p)).collect(Collectors.toList()))
				.event(lead.getEvent().getStatus())
				.date(Converter.toString(lead.getDate()))
				.visit(new DueDateDTO(lead.getVisit()))
				.dueDate(new DueDateDTO(lead.getDueDate()))
				.estimator(lead.getEstimator()==null?null:UserDTO.of(lead.getEstimator()))
				.company(CompanyDTO.of(lead.getCompany()))
				.contact(PersonDTO.of(lead.getContact()))
				.title(lead.getTitle())
				.address(lead.getAddress())
				.medias(MediaDTO.of(lead.getMedias()))
				.invitations(InvitationDTO.of(lead.getInvitations()))
				.totalPrice(String.valueOf(getTotalPrice(lead)))
				.build();
	}
	
	private static long getTotalPrice(Lead lead) {
		if(lead.getProposals()==null || lead.getProposals().size()<=0)
			return BigDecimal.ZERO.longValue();
		
		List<Proposal> acceptedProposals = lead.getProposals().stream().filter(p->!p.isChangeorder()).filter(p->p.isAccepted()).collect(Collectors.toList());
		if(acceptedProposals!=null && acceptedProposals.size()>0) {
			return acceptedProposals.stream().map(Proposal::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
		}else {
			return lead.getProposals().stream().filter(p->!p.isChangeorder()).sorted(Comparator.reverseOrder()).findFirst().orElse(Proposal.builder().total(BigDecimal.ZERO).build()).getTotal().longValue();
		}
	}

	public static final Lead toLead(LeadDTO leadDTO) throws LeadsException {
		if(leadDTO==null)
			return null;
		Lead lead = new Lead();
		lead.setClient(PersonDTO.toPerson(leadDTO.getClient()));
		lead.setDate(Converter.toLocalDateTime(leadDTO.getDate()));
		lead.setDescription(leadDTO.getDescription());
		lead.setEstimator(UserDTO.toUser(leadDTO.getEstimator()));
		lead.setEvent(!StringUtils.hasText(leadDTO.getEvent())?null:Event.valueOf(leadDTO.getEvent()));
		lead.setId(!StringUtils.hasText(leadDTO.getId())?null:Long.valueOf(leadDTO.getId()));
		lead.setNotes(leadDTO.getNotes());
		lead.setProposals(leadDTO.getProposals()==null?null:leadDTO.getProposals().stream().map(p->ProposalDTO.toProposal(p)).collect(Collectors.toList()));
		lead.setVisit(new DueDateDTO(leadDTO.getVisit().getDate()).toLocalDateTime());
		lead.setDueDate(new DueDateDTO(leadDTO.getDueDate().getDate()).toLocalDateTime());
		lead.setCompany(CompanyDTO.to(leadDTO.getCompany()));
		lead.setContact(PersonDTO.toPerson(leadDTO.getContact()));
		lead.setTitle(leadDTO.getTitle());
		lead.setAddress(leadDTO.getAddress());
		lead.setMedias(leadDTO.getMedias().stream().map(media->MediaDTO.toMedia(media)).collect(Collectors.toList()));
		lead.setInvitations(InvitationDTO.toInvitation(leadDTO.getInvitations()));
		return lead;
	}

}
