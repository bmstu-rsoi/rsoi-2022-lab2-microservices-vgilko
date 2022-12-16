package ru.gilko.carsimpl.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsimpl.domain.Car;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        setUpMapper(mapper);

        TypeMap<Car, CarOutDto> carTypeMap = mapper.createTypeMap(Car.class, CarOutDto.class);
        carTypeMap.addMapping(Car::isAvailability, CarOutDto::setAvailable);

        return mapper;
    }

    private void setUpMapper(ModelMapper mapper) {
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }
}
