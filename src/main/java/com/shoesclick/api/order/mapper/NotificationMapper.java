package com.shoesclick.api.order.mapper;

import com.shoesclick.api.order.entity.Notification;
import com.shoesclick.notification.avro.NotificationAvro;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {

    NotificationAvro map(Notification notification);
}
