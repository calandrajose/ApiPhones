package com.phones.phones.projection;

import java.util.Date;

public interface CallsBetweenDates {

    String getOriginLine();
    String getDestinationLine();
    Float getRate();
    Integer getDuration();
    Float getTotalPrice();
    Date geCreationDate();
}
