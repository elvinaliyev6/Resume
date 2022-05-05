package com.company.dto;

public class ResponseDTO {

    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object obj;


    private ResponseDTO(){

    }

    public static ResponseDTO of(Object obj){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setObj(obj);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj,String successMessage){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setObj(obj);
        responseDTO.setSuccessMessage(successMessage);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj,Integer errorCode,String errorMessage){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setObj(obj);
        responseDTO.setErrorMessage(errorMessage);
        responseDTO.setErrorCode(errorCode);
        return responseDTO;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", successMessage='" + successMessage + '\'' +
                ", obj=" + obj +
                '}';
    }
}
