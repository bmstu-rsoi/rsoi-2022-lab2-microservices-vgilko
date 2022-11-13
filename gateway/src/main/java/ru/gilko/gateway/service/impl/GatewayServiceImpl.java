package ru.gilko.gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.gilko.cars.controller.feign.CarFeign;
import ru.gilko.cars.dto.CarOutDto;
import ru.gilko.cars.dto.PageableCollectionOutDto;
import ru.gilko.gateway.dto.CarDto;
import ru.gilko.gateway.service.api.GatewayService;

@Service
@Slf4j
public class GatewayServiceImpl implements GatewayService {
    private final CarFeign carFeign;
    private final ModelMapper modelMapper;

    public GatewayServiceImpl(CarFeign carFeign, ModelMapper modelMapper) {
        this.carFeign = carFeign;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size) {
        Page<CarOutDto> carOutDtos = carFeign.getCars(showAll, page, size);

        log.info("Received {} entities from car service", carOutDtos.getSize());

        return mapToPageCollectionOutDto(carOutDtos, CarDto.class);
    }

    private <T> PageableCollectionOutDto<T> mapToPageCollectionOutDto(Page<CarOutDto> page, Class<T> destinationClass) {
        Page<T> mappedPage = page.map(car -> modelMapper.map(car, destinationClass));

        return buildPageCollectionOutDto(mappedPage);
    }

    private <T> PageableCollectionOutDto<T> buildPageCollectionOutDto(Page<T> page) {
        return new PageableCollectionOutDto<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
