package com.example.exintermediate.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Hotel;

/**
 * hotelsテーブルへのDBアクセス（SQL実行）を担当するRepository。
 *
 * <p>責務：SQLを書いてDBからデータを取る／DBへデータを書く。</p>
 *
 * @author ishida
 */
@Repository
public class HotelRepository {

    private final NamedParameterJdbcTemplate template;

    /**
     * NamedParameterJdbcTemplateをDIで受け取る。
     *
     * @param template NamedParameterJdbcTemplate
     */
    public HotelRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * ResultSetの1行をHotelに変換するルール。
     */
    private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setAreaName(rs.getString("area_name"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setNearestStation(rs.getString("nearest_station"));
        hotel.setPrice(rs.getInt("price"));
        hotel.setParking(rs.getString("parking"));
        return hotel;
    };

    /**
     * 全件取得（価格の安い順、同価格はid順）。
     *
     * @return ホテル一覧（全件）
     */
    public List<Hotel> findAllOrderByPriceAsc() {
        String sql = """
                SELECT id, area_name, hotel_name, address, nearest_station, price, parking
                FROM hotels
                ORDER BY price ASC, id ASC
                """;
        return template.query(sql, HOTEL_ROW_MAPPER);
    }

    /**
     * 指定価格以下のホテルを取得（価格の安い順、同価格はid順）。
     *
     * @param price 価格（円）
     * @return 指定価格以下のホテル一覧
     */
    public List<Hotel> findByPriceLessThanEqualOrderByPriceAsc(Integer price) {
        String sql = """
                SELECT id, area_name, hotel_name, address, nearest_station, price, parking
                FROM hotels
                WHERE price <= :price
                ORDER BY price ASC, id ASC
                """;
        MapSqlParameterSource param = new MapSqlParameterSource("price", price);
        return template.query(sql, param, HOTEL_ROW_MAPPER);
    }
}
