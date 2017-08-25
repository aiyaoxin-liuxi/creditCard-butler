package com.zhl.card.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.httpclient.HttpException;
import org.pub.util.file.HclientFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    
    private Logger logs = LoggerFactory.getLogger(UploadUtil.class);
    
    public String saveFile(String keySn, MultipartFile file){
        String fileId =null;
        if(!file.isEmpty()){
            
            // 生成文件名称,当前时间年月日+5位UUID
            String name = Stringer.getNow("yyyyMMdd")+UUID.randomUUID().toString().substring(30);
//            String fileType = file.getContentType();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            logs.info("上传文件类型...keySn = " + keySn + ",fileType = " + suffix);
            if(!Stringer.isImgSuffix(suffix)){
                return fileId;
            }
            File f = null;
            try {
                f = multipartToFile(file);
                logs.info("上传文件生成file文件...keySn = " + keySn + ",file = " + f);
                // 上传文件服务器
                String uploadFileMethod = HclientFileUtil.uploadFileMethod("userinfo", f);
                // 返回文件路径
                fileId = Constant.IMAGE_SERVER_URL_LOAD + uploadFileMethod;
                logs.info("上传文件返回路径...keySn = " + keySn + ",fileId = " + fileId);
            } catch (Exception e) {
                logs.info(e.getMessage());
            }
        }
        return fileId;
    }
    
    
    private File multipartToFile(MultipartFile file) throws IOException {
        File f = null;
        try {
            f = File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();        
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
//    private File multipartToFile(MultipartFile multfile) throws IOException {
//        CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
//        // 这个myfile是MultipartFile的
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        File file = fi.getStoreLocation();
//        // 手动创建临时文件
//        if (file.length() < CommonConstants.MIN_FILE_SIZE) {
//            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator")
//                    + file.getName());
//            multfile.transferTo(tmpFile);
//            return tmpFile;
//        }
//        return file;
//    }

}
