package ru.gilko.rentalimpl.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gilko.rentalapi.dto.RentalOutDto;
import ru.gilko.rentalimpl.domain.Rental;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        setUpMapper(mapper);

        TypeMap<Rental, RentalOutDto> rentalTypeMap =
                mapper.createTypeMap(Rental.class, RentalOutDto.class);
        rentalTypeMap.addMapping(Rental::getStatus, RentalOutDto::setStatus);

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
