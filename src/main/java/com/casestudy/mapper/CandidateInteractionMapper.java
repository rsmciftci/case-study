package com.casestudy.mapper;

import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateInteractionSavingDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.CandidateInteraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateInteractionMapper {

    CandidateInteractionMapper INSTANCE = Mappers.getMapper(CandidateInteractionMapper.class);

    CandidateInteraction convertCandidateWithInteractionDtoToCandidateInteraction(CandidateWithInteractionDto candidateWithInteractionDto);

    @Mapping(source = "candidateId", target = "candidate.id")
    CandidateInteraction convertCandidateInteractionSavingDtoToCandidateInteraction(CandidateInteractionSavingDto candidateInteractionSavingDto);
    @Mapping(source = "candidateId", target = "candidate.id")
    CandidateInteraction convertCandidateInteractionDtoToCandidateInteraction(CandidateInteractionDto candidateInteractionDto);
}
