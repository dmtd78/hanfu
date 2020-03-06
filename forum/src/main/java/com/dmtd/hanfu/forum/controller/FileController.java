package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.csource.fastdfs.pool.Connection;
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

    /**
     * 文件上传
     *
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        JsonResult jsonResult = new JsonResult();
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String filePath= uploadFile(bytes, ext_Name, file_Name);

        try {
            //加载fastDFS客户端的配置文件
//            ClientGlobal.initByProperties("conf/fastdfs-client.properties");

            //创建tracker的客户端
//            TrackerClient tracker = new TrackerClient();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131",22122);
            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
            Connection connection = new TrackerServer(inetSocketAddress).getConnection();
//            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvp [] = new NameValuePair[]{
                    new NameValuePair("fileName", file_Name),
            };
            //执行上传
//            String fileIds[] = storageClient.upload_file(local_filename, "txt", nvp);
            String fileIds[] = storageClient.upload_file(bytes,ext_Name,nvp);

            System.out.println("上传组名：" + fileIds[0]);
            System.out.println("上传路径: " + fileIds[1]);
//            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

//    public String uploadFile(byte[] byteFile, String ext_file, String file_Name) {
//
//        // 拼接服务区的文件路径
//        StringBuffer sbPath = new StringBuffer();
//        sbPath.append("http://106.12.61.131/");
//        try {
//            // 初始化文件资源
//            ClientGlobal.initByProperties("application.properties");
//
//            // 链接FastDFS服务器，创建tracker和Stroage
//            TrackerClient trackerClient = new TrackerClient();
////            TrackerServer trackerServer = trackerClient.getConnection();
//            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131",22122);
//            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
//
//            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
//            StorageServer storageServer = getStorageServer(storageServerIp);
//            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//
//            //利用字节流上传文件
////            NameValuePair[] nvps = new NameValuePair[1];
////            nvps[0] = new NameValuePair(file_Name, ext_file);
//            String[] strings = storageClient.upload_file(byteFile, ext_file, null);
//
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//        }
//        return sbPath.toString();
//    }

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
//        log.debug("——storage server生成");
        return storageServer;
    }

//    /**
//     * 获得可用的storage IP
//     *
//     * @param trackerClient
//     * @param trackerServer
//     * @return 返回storage IP
//     */
//    private static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
//        String storageIp = null;
//        if (trackerClient != null && trackerServer != null) {
//            try {
//                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "group1");
//                storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        log.debug("——获取组中可用的storage IP——" + storageIp);
//        return storageIp;
//    }


    //要上传的文件地址
    public static String local_filename = "E:\\test.txt";
    public static void testUpload() {
        try {
            //加载fastDFS客户端的配置文件
//            ClientGlobal.initByProperties("conf/fastdfs-client.properties");

            //创建tracker的客户端
//            TrackerClient tracker = new TrackerClient();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("106.12.61.131",22122);
            TrackerServer trackerServer = new TrackerServer(inetSocketAddress);
            Connection connection = new TrackerServer(inetSocketAddress).getConnection();
//            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvp [] = new NameValuePair[]{
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
