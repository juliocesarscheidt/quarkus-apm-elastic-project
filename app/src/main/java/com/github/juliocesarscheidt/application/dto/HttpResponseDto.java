package com.github.juliocesarscheidt.application.dto;

public class HttpResponseDto {

	private Integer statusCode;
    private Object data;
    
    public HttpResponseDto() {
    }
    
    public HttpResponseDto(Integer statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }
    
    public HttpResponseDto(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    public HttpResponseDto(Object data) {
        this.data = data;
    }

    public Integer getStatusCode() {
		return statusCode;
	}

    public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "HttpResponseDto [statusCode=" + statusCode + ", data=" + data + "]";
	}
}
