package com.dmtd.hanfu.forum.exception;

/**
 * <br>
 * Copyright (c) 12/27/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.exception <br>
 * FileName: JsonResult <br>
 * <br>
 *
 * @author duanmin
 * @created 12/27/2019-10:15 AM
 * @last Modified
 * @history
 */
public class JsonResult {
    protected int resultCode;
    protected String errorInfo;
    public int getResultCode() {
        return resultCode;
    }
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    public String getErrorInfo() {
        return errorInfo;
    }
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
    /**
     * 构造一个成功的结果返回
     */
    public JsonResult() {
        this.setResultCode(0);
    }
    /**
     * 构造一个有异常信息的结果返回
     *
     * @param errorInfo
     */
    public JsonResult(String errorInfo) {
        this.setResultCode(-1);
        this.setErrorInfo(errorInfo);
    }
}
