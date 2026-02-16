package com.example.exintermediate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.repository.TeamRepository;

/**
 * 
 * @author Ishida
 * チーム画面のユースケース（一覧表示・詳細表示）を担当するService。
 *
 * <p>Controllerからの依頼を受けて、Repositoryを呼び、必要なデータを返します。</p>
 */
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    /**
     * DIでTeamRepositoryを受け取ります。
     *
     * @param teamRepository TeamRepository
     */
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * 一覧表示用：発足日（古い順）のチーム一覧を返す。
     *
     * @return チーム一覧
     */
    public List<Team> showList() {
        return teamRepository.findAllOrderByInaugurationAsc();
    }

    /**
     * 詳細表示用：指定IDのチーム情報を返す。
     *
     * <p>存在しないIDの場合は例外にして「想定外」を早く発見できるようにする。</p>
     *
     * @param id チームID
     * @return チーム情報
     * @throws IllegalArgumentException 指定IDが存在しない場合
     */
    public Team showDetail(Integer id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定したチームが存在しません。id=" + id));
    }
}
