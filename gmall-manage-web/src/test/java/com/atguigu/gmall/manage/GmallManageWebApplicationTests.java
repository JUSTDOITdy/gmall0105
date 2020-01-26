package com.atguigu.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {
        //配置fdfs的全局链接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        ClientGlobal.init(tracker);
        TrackerClient trackerClient = new TrackerClient();
        //获得一个trackerServer的实例
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        //通过tracker获得一个Storage的链接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);

        String url = "http://192.168.2.128";

        String[] uploadInfos = storageClient.upload_file("e:/a.jpg", "jpg", null);
        for (String uploadInfo : uploadInfos){
            url +="/" + uploadInfo;
        }

        System.out.println(url);
    }

}
