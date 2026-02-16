package com.example.exintermediate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.exintermediate.service.TeamService;

/**
 * @author Ishida
 * チーム一覧／詳細画面のルーティング（URL設計）と表示を担当するController。
 *
 * <p>Controllerの役割は「URLを受け取って、画面に必要なデータを詰めて返す」こと。</p>
 */
@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    /**
     * DIでTeamServiceを受け取る。
     *
     * @param teamService TeamService
     */
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * 一覧画面を表示。
     *
     * <p>アクセス例：/ex-intermediate/teams</p>
     *
     * @param model 画面へ渡すデータを入れる箱
     * @return templates/teams/list.html
     */
    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("teams", teamService.showList());
        return "teams/list";
    }

    /**
     * 詳細画面を表示。
     *
     * <p>アクセス例：/ex-intermediate/teams/1</p>
     *
     * @param id URLに埋め込まれたチームID
     * @param model 画面へ渡すデータを入れる箱
     * @return templates/teams/detail.html
     */
    @GetMapping("/{id}")
    public String showDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("team", teamService.showDetail(id));
        return "teams/detail";
    }
}
