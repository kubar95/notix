package com.kubar95.notix.api.model;

import lombok.Data;




@Data
public class EventDTO {
    private String title;
    private String description;
    private Long startDate;
}
