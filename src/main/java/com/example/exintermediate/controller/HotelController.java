package com.example.exintermediate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.service.HotelService;

/**
 * ホテル検索画面のルーティングと表示を担当するController。
 *
 *
 * <ul>
 *   <li>GET /hotels : 検索フォーム画面</li>
 *   <li>POST /hotels/search : 結果画面へ遷移（hotels/result）</li>
 * </ul>
 *
 * @author ishida
 */
@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    /**
     * DIでHotelServiceを受け取る。
     *
     * @param hotelService HotelService
     */
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * 検索フォーム画面（初期表示）。
     *
     * <p>アクセス例：/ex-intermediate/hotels</p>
     *
     * @return templates/hotels/search.html
     */
    @GetMapping("")
    public String showSearchPage() {
        return "hotels/search";
    }

    /**
     * 検索実行：結果画面へ遷移して表示する。
     *
     * <p>空欄で検索した場合は全件検索。</p>
     *
     * @param price 入力された「円以下」（空欄なら ""）
     * @param model 画面へ渡すデータ
     * @return templates/hotels/result.html
     */
    @PostMapping("/search")
    public String search(@RequestParam(name = "price", required = false) String price, Model model) {

        Integer priceInt = parsePriceOrNull(price);

        List<Hotel> hotels = hotelService.searchByLessThanPrice(priceInt);

        model.addAttribute("price", priceInt);   // 見出し表示用
        model.addAttribute("hotels", hotels);    // 結果表示用

        return "hotels/result";
    }

    /**
     * 文字列の価格入力をIntegerに変換する。
     *
     * <p>未入力/空白/数字以外はnull扱いにして「全件検索」へ寄せる。</p>
     *
     * @param price 入力値
     * @return Integer（変換できない場合はnull）
     */
    private Integer parsePriceOrNull(String price) {
        if (price == null) return null;

        String trimmed = price.trim();
        if (trimmed.isEmpty()) return null;

        try {
            return Integer.valueOf(trimmed);
        } catch (NumberFormatException e) {
            // 入力が数字じゃない場合も、今回は「未入力扱い（全件）」にする
            return null;
        }
    }
}
