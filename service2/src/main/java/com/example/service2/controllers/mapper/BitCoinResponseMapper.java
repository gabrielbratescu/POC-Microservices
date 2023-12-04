package com.example.service2.controllers.mapper;


import com.example.service2.controllers.response.BitCoinResponse;
import com.example.service2.model.beans.Bitcoin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BitCoinResponseMapper {

    BitCoinResponseMapper INSTANCE = Mappers.getMapper(BitCoinResponseMapper.class);

    public List<BitCoinResponse> mapToBitCoinResponse(List<Bitcoin> bitcoin);

    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "amount", target = "price")
    @Mapping(source = "createdAt", target = "date")
    public BitCoinResponse mapToBitCoinResponse1(Bitcoin bitcoin);



}
