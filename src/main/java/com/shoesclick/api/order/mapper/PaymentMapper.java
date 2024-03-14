package com.shoesclick.api.order.mapper;

import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.payment.avro.PaymentAvro;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    PaymentAvro map(Payment request);

    default Map<String,String> map(Map<String,Object> map){
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
    }

}
