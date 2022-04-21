package com.geekbrains.enums;

import lombok.Data;
import lombok.Getter;

public enum CategoryType {
    FOOD(1, "Food"),
    ELECTRONICS(2, "Electronics");

    @Getter
    private final Integer id;
    @Getter
    private final String title;


    CategoryType(Integer id, String title) {
        this.id = id;
        this.title= title;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
