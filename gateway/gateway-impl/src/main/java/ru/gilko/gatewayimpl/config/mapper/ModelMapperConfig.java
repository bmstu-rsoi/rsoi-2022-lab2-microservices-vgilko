package ru.gilko.gatewayimpl.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.rentalapi.dto.RentalOutDto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        setUpMapper(mapper);

        TypeMap<RentalOutDto, RentalCreationOutDto> rentalTypeMap =
                mapper.createTypeMap(RentalOutDto.class, RentalCreationOutDto.class);
        rentalTypeMap.addMapping(RentalOutDto::getStatus, RentalCreationOutDto::setStatus);

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
