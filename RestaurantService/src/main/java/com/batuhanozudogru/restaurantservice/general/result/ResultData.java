package com.batuhanozudogru.restaurantservice.general.result;




public class ResultData<T> extends Result{
    private T data;
    public ResultData(boolean status, String code, String message, T data) {
        super(status, code, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

}

