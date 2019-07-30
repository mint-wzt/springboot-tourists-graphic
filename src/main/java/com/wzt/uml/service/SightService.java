package com.wzt.uml.service;

import com.wzt.uml.dao.ViewSpotMapper;
import com.wzt.uml.model.ViewSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SightService {

    @Autowired
    private ViewSpotMapper viewSpotMapper;
    @Autowired
    private SightFileService sightFileService;

    public List<ViewSpot> getSightByName(String city){
        return viewSpotMapper.getSightByName(city);
    }

    public List<String> getPhoPath(List<ViewSpot> viewSpotList){
        if (viewSpotList.size()!=0){
            List<String> viewList = sightFileService.getFirstPic(viewSpotList);
//            for (int i=0;i<viewList.size();i++){
//                viewList.set(i,viewList.get(i).replaceAll("d:",""));
//                System.out.println(i+":"+viewList.get(i));
//            }
            return viewList;
        }else {
            //景点不存在
            return null;
        }
    }

    public ViewSpot getSightInfo(int id){
        return viewSpotMapper.getSightByID(id);
    }

    public void getInfo(ViewSpot viewSpot, Model model){
        if (viewSpot == null){
            //景点不存在
        }else {
            String intro = viewSpot.getIntro();
            String photo = viewSpot.getPhoto();
            try {
                List<String> context = sightFileService.getFileContext(intro);
                List<String> picture = sightFileService.getAllPic(photo);
//                for (int i=0;i<picture.size();i++) {
//                    picture.set(i, picture.get(i).replaceAll("d:", ""));
//                }
                byte[] string = context.toString().getBytes("UTF-8");
                String s = new String(string,"UTF-8");
                model.addAttribute("context",context);
                System.out.println("context: "+ s);
                model.addAttribute("picture",picture);
                model.addAttribute("name",viewSpot.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
