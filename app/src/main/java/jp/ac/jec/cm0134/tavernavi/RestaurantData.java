package jp.ac.jec.cm0134.tavernavi;

public class RestaurantData {

    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private String category;
    private String mobile;
    private String shop_image1;
    private String shop_image2;
    private String address;
    private String tel;
    private String opentime;
    private String holiday;
    private String line;
    private String station;
    private String walk;
    private String pr_short;
    private String pr_long;
    private String areaname;
    private String prefname;
    private String areaname_s;
    private int budget;
    private int lunch;
    private String credit_card;
    private String e_money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShop_image1() {
        return shop_image1;
    }

    public void setShop_image1(String shop_image1) {
        this.shop_image1 = shop_image1;
    }

    public String getShop_image2() {
        return shop_image2;
    }

    public void setShop_image2(String shop_image2) {
        this.shop_image2 = shop_image2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

    public String getPr_short() {
        return pr_short;
    }

    public void setPr_short(String pr_short) {
        this.pr_short = pr_short;
    }

    public String getPr_long() {
        return pr_long;
    }

    public void setPr_long(String pr_long) {
        this.pr_long = pr_long;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getPrefname() {
        return prefname;
    }

    public void setPrefname(String prefname) {
        this.prefname = prefname;
    }

    public String getAreaname_s() {
        return areaname_s;
    }

    public void setAreaname_s(String areaname_s) {
        this.areaname_s = areaname_s;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(Object lunch) {
        if (lunch.equals("")) {
            this.lunch = 0;
        } else {
            this.lunch = (int)lunch;
        }
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public String getE_money() {
        return e_money;
    }

    public void setE_money(String e_money) {
        this.e_money = e_money;
    }
}
