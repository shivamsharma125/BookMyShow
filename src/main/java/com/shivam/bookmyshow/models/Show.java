package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Show extends BaseModel{
    private Movie movie;
//    private Theatre theatre; -> Screen contains the theatre
    private Screen screen;
    private Date startingTime;
    private List<Feature> features;
    private ShowStatus showStatus;
    private Language language;
    private List<ShowSeat> showSeats;
    private List<ShowSeatType> showSeatTypes;
}
