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
    protected String resultInfo;
    public int getResultCode() {
        return resultCode;
    }
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    public String getResultInfo() {
        return resultInfo;
    }
    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
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
     * @param resultInfo
     */
    public JsonResult(String resultInfo) {
        this.setResultCode(-1);
        this.setResultInfo(resultInfo);
    }
}
