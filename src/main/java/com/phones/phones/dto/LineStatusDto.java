package com.phones.phones.dto;

import com.phones.phones.model.LineStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LineStatusDto implements Serializable {

    private LineStatus status;

}
