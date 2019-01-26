package io.shopr.controllers.transferobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryRequestDto {
    private String name;

    private CategoryRequestDto(){
        // json
    }
}
