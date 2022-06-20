package com.github.juliocesarscheidt.application.dto;

public class HttpResponseDtoBuilder {

	private Integer statusCode;
    private Object data;
	
    public HttpResponseDtoBuilder() {
    }
    
    public HttpResponseDtoBuilder(Integer statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }
    
    public HttpResponseDtoBuilder(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    public HttpResponseDtoBuilder(Object data) {
        this.data = data;
    }

    public Integer getStatusCode() {
		return statusCode;
	}

	public HttpResponseDtoBuilder setStatusCode(int i) {
		this.statusCode = i;
		return this;
	}

	public Object getData() {
		return data;
	}

	public HttpResponseDtoBuilder setData(Object data) {
		this.data = data;
		return this;
	}

	public HttpResponseDto build() {
    	HttpResponseDto response = new HttpResponseDto();
    	response.setData(this.data);
    	response.setStatusCode(this.statusCode);
    	return response;
    }
}
