package com.dmtd.hanfu.forum.exception;

/**
 * <br>
 * Copyright (c) 12/27/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.exception <br>
 * FileName: JsonResultData <br>
 * <br>
 *
 * @author duanmin
 * @created 12/27/2019-2:30 PM
 * @last Modified
 * @history
 */
public class JsonResultData {
    protected int resultCode;
    protected String resultInfo;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 构造一个成功的结果返回
     */
    public JsonResultData() {
        this.setResultCode(0);
    }
    /**
     * 构造一个有异常信息的结果返回
     *
     * @param resultInfo
     */
    public JsonResultData(String resultInfo) {
        this.setResultCode(-1);
        this.setResultInfo(resultInfo);
    }
}
