package com.kramti.exception;

import com.kramti.dto.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDto(errorMessage))
                .build();
    }
}
