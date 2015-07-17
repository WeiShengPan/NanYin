package cn.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 玮晟 on 2015/7/17.
 */
@Controller
public class FileUploadController
{
    @RequestMapping(value="nyadmin/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("fileName") String fileName,@RequestParam("file") MultipartFile file)
    {
        if(!file.isEmpty())
        {
            try{
                byte[] bytes=file.getBytes();
                BufferedOutputStream stream=new BufferedOutputStream((new FileOutputStream(new File(fileName))));
                stream.write(bytes);
                stream.close();
                return "successful upload "+ fileName;

            }
            catch (Exception e)
            {
                return "failed to upload "+ fileName +"=>"+e.getMessage();
            }
        }
        else
            return "empty file: "+ fileName;
    }

}
