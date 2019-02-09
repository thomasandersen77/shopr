package io.shopr.controllers.transferobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRequestDto {
    private String name;

    public CategoryRequestDto(String name){
        this.name = name;
    }
}
