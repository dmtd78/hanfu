package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.dto.FileDTO;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.util.FileUtils;
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
        FileDTO fileDTO = new FileDTO();
        fileDTO.setPath(FileUtils.getFilePath(file));
        return jsonResult;
    }

}
