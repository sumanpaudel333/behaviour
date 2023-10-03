package com.behaviour.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCountDto implements Serializable {
    private String name;
    private String description;
    private Long orderCount;
}
