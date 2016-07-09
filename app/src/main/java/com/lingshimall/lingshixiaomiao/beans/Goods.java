package com.lingshimall.lingshixiaomiao.beans;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class Goods {

    /**
     * begin_hour :
     * desc :
     * fav_num : 0
     * freight : 0
     * guide_type : 0
     * id : 8085
     * img : {"img_h":640,"img_url":"http://img.lingshi.cccwei.com/lingshi/ad5/d5/5/c457e2ed8cbad75e853f14bfe86a7ad5.jpeg","img_w":640}
     * price : {"current":26.5,"prime":49.5}
     * sold_num : 0
     * special_num : 925
     * special_percentage : 63
     * status : 0
     * surplus_num : 0
     * tag : {"color":1,"title":"5.4折"}
     * time : 1468029600
     * title : 韩国进口八道御膳章鱼海鲜拉面 大包装
     * type : 2
     */

    private String begin_hour;
    private String desc;
    private int fav_num;
    private int freight;
    private int guide_type;
    private int id;
    /**
     * img_h : 640
     * img_url : http://img.lingshi.cccwei.com/lingshi/ad5/d5/5/c457e2ed8cbad75e853f14bfe86a7ad5.jpeg
     * img_w : 640
     */

    private ImgBean img;
    /**
     * current : 26.5
     * prime : 49.5
     */

    private PriceBean price;
    private int sold_num;
    private int special_num;
    private int special_percentage;
    private int status;
    private int surplus_num;
    /**
     * color : 1
     * title : 5.4折
     */

    private TagBean tag;
    private int time;
    private String title;
    private int type;

    public String getBegin_hour() {
        return begin_hour;
    }

    public void setBegin_hour(String begin_hour) {
        this.begin_hour = begin_hour;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFav_num() {
        return fav_num;
    }

    public void setFav_num(int fav_num) {
        this.fav_num = fav_num;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getGuide_type() {
        return guide_type;
    }

    public void setGuide_type(int guide_type) {
        this.guide_type = guide_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImgBean getImg() {
        return img;
    }

    public void setImg(ImgBean img) {
        this.img = img;
    }

    public PriceBean getPrice() {
        return price;
    }

    public void setPrice(PriceBean price) {
        this.price = price;
    }

    public int getSold_num() {
        return sold_num;
    }

    public void setSold_num(int sold_num) {
        this.sold_num = sold_num;
    }

    public int getSpecial_num() {
        return special_num;
    }

    public void setSpecial_num(int special_num) {
        this.special_num = special_num;
    }

    public int getSpecial_percentage() {
        return special_percentage;
    }

    public void setSpecial_percentage(int special_percentage) {
        this.special_percentage = special_percentage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSurplus_num() {
        return surplus_num;
    }

    public void setSurplus_num(int surplus_num) {
        this.surplus_num = surplus_num;
    }

    public TagBean getTag() {
        return tag;
    }

    public void setTag(TagBean tag) {
        this.tag = tag;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ImgBean {
        private int img_h;
        private String img_url;
        private int img_w;

        public int getImg_h() {
            return img_h;
        }

        public void setImg_h(int img_h) {
            this.img_h = img_h;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getImg_w() {
            return img_w;
        }

        public void setImg_w(int img_w) {
            this.img_w = img_w;
        }
    }

    public static class PriceBean {
        private double current;
        private double prime;

        public double getCurrent() {
            return current;
        }

        public void setCurrent(double current) {
            this.current = current;
        }

        public double getPrime() {
            return prime;
        }

        public void setPrime(double prime) {
            this.prime = prime;
        }
    }

    public static class TagBean {
        private int color;
        private String title;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
