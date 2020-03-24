package com.dmtd.hanfu.forum.util;

import com.dmtd.hanfu.forum.controller.FileController;
import com.dmtd.hanfu.forum.dto.FileDTO;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Copyright (c) 3/24/2020 All Rights Reserved By dmtd
 * <p>
 * Package: com.dmtd.hanfu.forum.util
 * FileName: FileUtils
 *
 * @author duanmin
 * @description
 * @created 3/24/2020-1:51 PM
 * @last Modified
 * @history
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 得到Storage服务
     *
     * @param storageIp
     * @return 返回Storage服务
     */
    private static StorageServer getStorageServer(String storageIp) {
        StorageServer storageServer = null;
        if (storageIp != null && !("").equals(storageIp)) {
            try {
                // ip port store_path下标
                storageServer = new StorageServer(storageIp, 23000, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("——storage server生成");
        return storageServer;
    }

    /**
     * @param file
     * @return
     */
    public static String getFilePath(MultipartFile file) {
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = null;
        try {
            //创建tracker的客户端
            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131", 22122);
            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
            String storageServerIp = null;
            StorageServer storageServer = FileUtils.getStorageServer(storageServerIp);
            //定义storage客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvp[] = new NameValuePair[]{
                    new NameValuePair("fileName", file_Name),
            };
            //执行上传
            String fileIds[] = storageClient.upload_file(bytes, ext_Name, nvp);

            System.out.println("上传组名：" + fileIds[0]);
            System.out.println("上传路径: " + fileIds[1]);
            filePath = "http://106.12.61.131/" + fileIds[0] + "/" + fileIds[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
