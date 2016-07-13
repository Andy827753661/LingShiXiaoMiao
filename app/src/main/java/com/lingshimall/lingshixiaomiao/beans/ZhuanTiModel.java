
package com.lingshimall.lingshixiaomiao.beans;

/**
 * Created by zhai on 2016/7/12.
 */
public class ZhuanTiModel {
//    "id": 596,
//            "desc": "生活于热闹喧嚣的水泥丛林里，每天都埋首于工作当中，早出晚归，忙忙碌碌。但有时却感觉自己一无所获，失去的远远比得到的要多，似乎忘了要放下手头的工作！是时候适当的放松一下，盛夏繁华似锦，让我们放慢脚步，去感受夏的韵味、去品位美食带来的那些盛夏小确幸~~",
//            "title": "帮你挑|初夏，遇见美食的小确幸",
//            "img": {
//        "img_url": "http://img.lingshi.cccwei.com/lingshi/07c/7c/c/fbf9876b34634be9ee7a5c23f99bd07c.jpeg",
//                "img_w": 612,
//                "img_h": 306
//    "hotindex": 913,
//            "share_num": 0

    private int id;
    private String desc;
    private String title;
    private String img_url;
    private int hotindex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getHotindex() {
        return hotindex;
    }

    public void setHotindex(int hotindex) {
        this.hotindex = hotindex;
    }
}
