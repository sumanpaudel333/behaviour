package com.behaviour.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issues implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonAlias("name")
    private String name;
    @JsonAlias("description")
    private String description;
    @JsonAlias("order_count")
    private Long orderCount;
    @JsonAlias("priority")
    private String priority;
}
