package cn.nanyin.util;

/**
 * Created by 玮晟 on 2015/7/26.
 */
public class FileUploadResult extends JsonResult {

    private String fileName;

    public FileUploadResult(String result, String message, String fileName) {
        super(result, message);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
