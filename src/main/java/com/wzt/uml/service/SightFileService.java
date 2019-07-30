package com.wzt.uml.service;

import com.wzt.uml.model.ViewSpot;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SightFileService {

    public static final String PATH = "https://b1-q.mafengwo.net/s14/M00/F5/D6/wKgE2l0dvB6ALMVIAA82W2K6azY124.jpg?imageMogr2%2Fthumbnail%2F%21440x300r%2Fstrip%2Fgravity%2FCenter%2Fcrop%2F%21440x300%2Fquality%2F90";
    /**
     * 获取txt文件内容，包括图片信息和景点介绍信息
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> getFileContext(String path) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
        List<String> list = new ArrayList<>();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            if (str.length() > 0) {
                list.add(str);
            }
        }
        System.out.println("getFileContext: " +list);
        return list;
    }

    /**
     * 获取第一张图片
     * @param ViewSpotList
     * @return
     */
    public List<String> getFirstPic(List<ViewSpot> ViewSpotList) {
        List<String> pic = new ArrayList<>();
        for (int i = 0; i < ViewSpotList.size(); i++) {
            String txt = ViewSpotList.get(i).getPhoto();
            try {
                String phto = getFileContext(txt).get(0);
                if (phto != null) {
                    pic.add(phto);
                }
            } catch (Exception e) {
                pic.add(PATH);
                System.out.println("没有图片1");
                continue;
            } finally {
            }
        }
        return pic;
    }

    /**
     * 获取所有图片
     * @param path
     * @return
     */
    public List<String> getAllPic(String path) {
        List<String> pic = new ArrayList<String>();
        try {
            pic = getFileContext(path);
        } catch (Exception e) {
            System.out.println("没有图片2");
            pic.add(PATH);
        } finally {
        }
        if (pic == null || pic.size()==0){
            pic.add(PATH);
        }
        return pic;
    }
}
