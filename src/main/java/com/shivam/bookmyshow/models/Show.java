package com.shivam.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie; // [M:1]
//    private Theatre theatre; -> Screen contains the theatre
    @ManyToOne
    private Screen screen; // [M:1]
    private Date startingTime;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    @Enumerated(EnumType.ORDINAL)
    private ShowStatus showStatus;
    @Enumerated(EnumType.ORDINAL)
    private Language language;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats; // [1:M]
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes; // [1:M]
}
