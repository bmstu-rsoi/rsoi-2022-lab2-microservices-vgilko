package ru.gilko.gatewayapi.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableCollectionOutDto<T> {
    @JsonProperty("items")
    private List<T> content;

    private int page;
    private int pageSize;
    private int totalElements;
}
