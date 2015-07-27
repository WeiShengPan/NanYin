package cn.nanyin.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 玮晟 on 2015/7/26.
 */
public class FileUpload {
    private String basePath;
    private MultipartFile file;
    private HttpSession session;

    public FileUpload(String basePath,MultipartFile file,HttpSession session)
    {
        this.basePath=basePath;
        this.file=file;
        this.session=session;
    }

    public FileUploadResult upload()
    {
        if (file.isEmpty())
        {
            return new FileUploadResult("error", "上传失败，文件不能空", "");
        }
        else
        {
            try {
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                String fileName = UUID.randomUUID().toString();
                if (StringUtils.isNotEmpty(extension)) {
                    fileName += "." + extension;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
                String dateString = simpleDateFormat.format(new Date());
                String path = session.getServletContext().getRealPath(basePath + dateString);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                return new FileUploadResult("success", "上传成功", basePath + dateString + targetFile.getName());
            } catch (IOException e) {
                return new FileUploadResult("error", "，上传失败，未知错误", "");
            }
        }
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
