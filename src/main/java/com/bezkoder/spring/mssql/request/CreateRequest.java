package com.bezkoder.spring.mssql.request;

import lombok.Data;

@Data
public class CreateRequest {
    String carPayId;
    String carType;
    String carWheels;
    String carBodyMsg;
    String carBodyTrunk;
}
