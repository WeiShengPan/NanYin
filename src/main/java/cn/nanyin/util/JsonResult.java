package cn.nanyin.util;

/**
 * Created by 玮晟 on 2015/7/26.
 */
public class JsonResult {
    private String result;
    private String message;

    public JsonResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

