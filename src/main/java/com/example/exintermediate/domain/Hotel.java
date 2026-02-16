package com.example.exintermediate.domain;

/**
 * hotelsテーブルの1レコード（1行）を表すドメインクラス。
 *
 * DBの列（snake_case）をJavaで扱いやすいcamelCaseで持つ。
 *
 * @author ishida
 */
public class Hotel {

    /** 主キー（自動採番） */
    private Integer id;

    /** エリア名（例：首都圏） */
    private String areaName;

    /** ホテル名 */
    private String hotelName;

    /** 住所 */
    private String address;

    /** 最寄駅 */
    private String nearestStation;

    /** 価格（円） */
    private Integer price;

    /** 駐車場（例：あり／なし） */
    private String parking;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getNearestStation() { return nearestStation; }
    public void setNearestStation(String nearestStation) { this.nearestStation = nearestStation; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public String getParking() { return parking; }
    public void setParking(String parking) { this.parking = parking; }
}
