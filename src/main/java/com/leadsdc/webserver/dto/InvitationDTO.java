package com.leadsdc.webserver.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.leadsdc.webserver.model.Invitation;
import com.leadsdc.webserver.service.Converter;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InvitationDTO {

	private final Long id;
	private final String date;
	private final String dueDate;
	private final CompanyDTO company;
	private final PersonDTO contact;
	private final List<MediaDTO> medias;
	private final List<MediaDTO> proposals;
	private final Long emailed;
	private final BigDecimal price;
	
	public static final InvitationDTO of(Invitation invitation) {
		if(invitation == null) return null;
		return InvitationDTO.builder()
			.id(invitation.getId())
			.dueDate(Converter.dateToString(invitation.getDueDate(), Converter.MM_dd_yyyy_hh_mm))
			.date(Converter.dateToString(invitation.getDate(), Converter.MM_dd_yyyy_hh_mm))
			.company(CompanyDTO.of(invitation.getCompany()))
			.medias(MediaDTO.of(invitation.getMedias()))
			.proposals(MediaDTO.of(invitation.getProposals()))
			.contact(PersonDTO.of(invitation.getContact()))
			.emailed(invitation.getEmailed()==null?0L:invitation.getEmailed())
			.price(invitation.getPrice())
			.build();
	}
	
	public static final List<InvitationDTO> of(List<Invitation> invitations){
		if(invitations==null) return Collections.emptyList();
		return invitations.stream().map(inv->InvitationDTO.of(inv)).collect(Collectors.toList());
	}
	
	public static final List<Invitation> toInvitation(List<InvitationDTO> invitationDTOs){
		if(invitationDTOs==null) return Collections.emptyList();
		return invitationDTOs.stream().map(inv->InvitationDTO.toInvitation(inv)).collect(Collectors.toList());
	}
	
	public static final Invitation toInvitation(InvitationDTO invitationDTO) {
		if(invitationDTO==null)	return null;
		Invitation invitation = new Invitation();
		invitation.setId(invitationDTO.getId());
		invitation.setDueDate(Converter.convertToDate(invitationDTO.getDueDate(), Converter.MM_dd_yyyy_hh_mm));
		invitation.setDate(Converter.convertToDate(invitationDTO.getDate(), Converter.MM_dd_yyyy_hh_mm));
		invitation.setCompany(CompanyDTO.to(invitationDTO.getCompany()));
		invitation.setMedias(MediaDTO.toMedia(invitationDTO.getMedias()));
		invitation.setProposals(MediaDTO.toMedia(invitationDTO.getProposals()));
		invitation.setContact(PersonDTO.toPerson(invitationDTO.getContact()));
		invitation.setEmailed(invitationDTO.getEmailed()==null?0L:invitationDTO.getEmailed());
		invitation.setPrice(invitationDTO.getPrice());
		return invitation;
	}

}
