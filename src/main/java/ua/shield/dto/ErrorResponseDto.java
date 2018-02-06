package ua.shield.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponseDto {
    private Integer status;
    private String code;
    private List<ErrorDto> errors;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(HttpStatus status, String code, List<ErrorDto> errors) {
        this.status = status.value();
        this.code = code;
        this.errors = errors;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
