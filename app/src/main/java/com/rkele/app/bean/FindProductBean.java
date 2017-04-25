package com.rkele.app.bean;

/**
 * Created by ${mms} on 2017/3/2.
 */

public class FindProductBean {

    /**
     * id : a784cd35d0158e6b53560Y71173Y04
     * createTime : 2016-12-10 11:09:45
     * icon : a784cd35d0158e6b53531Z3RYIV27c.jpg
     * isUsed : 1
     * name : 金龙鱼爽滑挂面
     * iconName : 金龙鱼爽滑挂面450.jpg
     * volume1 : 450g/包
     * moneyDesc : 元/包
     * money : 0.01
     * code : 1004
     */

    private String id;
    private String createTime;
    private String icon;
    private String isUsed;
    private String name;
    private String iconName;
    private String volume1;
    private String moneyDesc;
    private double money;
    private String code;
    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getVolume1() {
        return volume1;
    }

    public void setVolume1(String volume1) {
        this.volume1 = volume1;
    }

    public String getMoneyDesc() {
        return moneyDesc;
    }

    public void setMoneyDesc(String moneyDesc) {
        this.moneyDesc = moneyDesc;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
