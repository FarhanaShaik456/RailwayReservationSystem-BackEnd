package com.cg.railwayreservation.train.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Train")
public class TrainModel {

    @Id
    @Pattern(regexp = "\\d{5}", message = "Train number should contain exactly 5 digits")
    private String trainNo;

    @NotBlank(message = "trainName cannot be empty")
    @Size(min = 3, max = 50, message = "trainName should be between 3 and 50 characters")
    private String trainName;

    @NotBlank(message = "trainFrom cannot be empty")
    @Size(min = 3, max = 30, message = "trainFrom should be between 3 and 30 characters")
    private String trainFrom;

    @NotBlank(message = "trainTo cannot be empty")
    @Size(min = 3, max = 30, message = "trainTo should be between 3 and 30 characters")
    private String trainTo;

    @Min(value = 0, message = "fare should be a positive number")
    @Max(value = 1000, message = "fare should not exceed 1000")
    private int fare;

    @Min(value = 0, message = "seats should be a positive number")
    @Max(value = 50, message = "seats should not exceed 50")
    private int seats;

    @NotBlank(message = "time cannot be empty")
    private String time;
}
