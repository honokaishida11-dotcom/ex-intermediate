package com.example.exintermediate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.repository.HotelRepository;

/**
 * ホテル検索のユースケース（業務処理）を担当するService。
 *
 * <p>Controllerから受け取った入力を元に、Repositoryを呼んで結果を返す。</p>
 *
 * @author ishida
 */
@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    /**
     * DIでHotelRepositoryを受け取る。
     *
     * @param hotelRepository HotelRepository
     */
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * 「価格が入力されたら価格以下」「未入力なら全件」の検索を行う。
     *
     * @param price 価格（円以下）。未入力ならnull
     * @return 検索結果
     */
    public List<Hotel> searchByLessThanPrice(Integer price) {
        if (price == null) {
            return hotelRepository.findAllOrderByPriceAsc();
        }
        return hotelRepository.findByPriceLessThanEqualOrderByPriceAsc(price);
    }
}
