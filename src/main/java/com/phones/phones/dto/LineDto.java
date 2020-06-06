package com.phones.phones.dto;

import com.phones.phones.model.LineStatus;
import com.phones.phones.model.LineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDto implements Serializable {

    private String number;
    private LineStatus status;
    private LineType lineType;

}
