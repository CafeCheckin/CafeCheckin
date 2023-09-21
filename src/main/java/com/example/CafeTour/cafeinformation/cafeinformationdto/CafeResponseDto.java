package com.example.CafeTour.cafeinformation.cafeinformationdto;

import com.example.CafeTour.cafeinformation.CafeInformation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeResponseDto {
    private Long id;
    private String cafeName;
    private String openAt;
    private String closeAt;
    private String telNum;
    private String cafeMood;
    private String address;
    private String latitude;
    private String longitude;
    private Double cafeGrade;

    @Builder
    public CafeResponseDto(CafeInformation cafeInformation) {
        this.id = cafeInformation.getId();
        this.cafeName=cafeInformation.getCafeName();
        this.openAt=cafeInformation.getOpenAt();
        this.closeAt=cafeInformation.getCloseAt();
        this.telNum=cafeInformation.getTelNum();
        this.cafeMood=cafeInformation.getCafeMood();
        this.address=cafeInformation.getAddress();
        this.latitude=cafeInformation.getLatitude();
        this.longitude=cafeInformation.getLongitude();
        this.cafeGrade=cafeInformation.getCafeGrade();
    }
}
