package com.example.demo.dto;

public class ResponseStructure<T> {
	int status;
	String message;
	private T data;
	
	public int getStatus() {
        return status;
    }
	public void setStatus(int status) {
        this.status = status;
    }
	public String getMessage() {
        return message;
    }

    public void setMessage(String i) {
        this.message = i;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
