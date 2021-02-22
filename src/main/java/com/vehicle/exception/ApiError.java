package com.vehicle.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String errorMessage;
    private HttpStatus status;
    private LocalDate currentDate = LocalDate.now();
}
