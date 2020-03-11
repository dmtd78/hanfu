package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.dto.FileDTO;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.csource.fastdfs.pool.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Copyright (c) 3/6/2020 All Rights Reserved By dmtd
 * <p>
 * Package: com.dmtd.hanfu.forum.controller
 * FileName: FileController
 *
 * @author duanmin
 * @description
 * @created 3/6/2020-10:22 AM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 文件上传
     *
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public JsonResultData upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        JsonResultData jsonResult = new JsonResultData();
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //创建tracker的客户端
            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131", 22122);
            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
            String storageServerIp = null;
            StorageServer storageServer = getStorageServer(storageServerIp);
            //定义storage客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvp[] = new NameValuePair[]{
                    new NameValuePair("fileName", file_Name),
            };
            //执行上传
            String fileIds[] = storageClient.upload_file(bytes, ext_Name, nvp);

            System.out.println("上传组名：" + fileIds[0]);
            System.out.println("上传路径: " + fileIds[1]);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setGroup(fileIds[0]);
            fileDTO.setPath("http://106.12.61.131/"+fileIds[0]+"/"+fileIds[1]);
            jsonResult.setData(fileDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

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


    //要上传的文件地址
    public static String local_filename = "E:\\test.txt";

    public static void testUpload() {
        try {
            //加载fastDFS客户端的配置文件
//            ClientGlobal.initByProperties("conf/fastdfs-client.properties");

            //创建tracker的客户端
//            TrackerClient tracker = new TrackerClient();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131", 22122);
            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
            Connection connection = new TrackerServer(inetSocketAddress).getConnection();
//            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvp[] = new NameValuePair[]{
                    new NameValuePair("fileName", "test.txt"),
            };
            //执行上传
            String fileIds[] = storageClient.upload_file(local_filename, "txt", nvp);

            System.out.println("上传组名：" + fileIds[0]);
            System.out.println("上传路径: " + fileIds[1]);
//            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
