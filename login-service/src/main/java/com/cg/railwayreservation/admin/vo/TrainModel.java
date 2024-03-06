package com.cg.railwayreservation.admin.vo;

import lombok.Data;

@Data
public class TrainModel {

   
    private String trainNo;
    private String trainName;
    private String trainFrom;
    private String trainTo;
    private int fare;
    private int seats;
    private String time;
}
