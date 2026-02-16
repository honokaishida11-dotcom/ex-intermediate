package com.example.exintermediate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Team;

/**
 * 
 * @author Ishida
 * teamsテーブルに対するSQL実行（DBアクセス）を担当するRepository。
 *
 * SQLを書いてDBからデータを取る／DBへデータを書く。
 * 
 */
@Repository
public class TeamRepository {

    private final NamedParameterJdbcTemplate template;

    /**
     * NamedParameterJdbcTemplate をDI（依存性注入）で受け取るコンストラクタ。
     *
     * @param template NamedParameterJdbcTemplate（:id など名前付きパラメータを扱える）
     */
    public TeamRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * ResultSet（DBの1行）→ Team（Javaオブジェクト）に詰め替える。
     *
     * <p>RowMapperは「DBの行を、Teamに変換する担当」
     */
    private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setLeagueName(rs.getString("league_name"));
        team.setTeamName(rs.getString("team_name"));
        team.setHeadquarters(rs.getString("headquarters"));
        team.setInauguration(rs.getString("inauguration"));
        team.setHistory(rs.getString("history"));
        return team;
    };

    /**
     * チーム一覧を「発足日が古い順（昇順）」で取得。
     *
     * PostgreSQLの to_date を使って日付に変換して並べ替え。</p>
     *
     * @return チーム一覧（発足日昇順）
     */
    public List<Team> findAllOrderByInaugurationAsc() {
        String sql = """
                SELECT id, league_name, team_name, headquarters, inauguration, history
                FROM teams
                ORDER BY to_date(inauguration, 'YYYY年MM月DD日') ASC
                """;
        return template.query(sql, TEAM_ROW_MAPPER);
    }

    /**
     * 指定IDのチームを1件取得。
     *
     * @param id チームID
     * @return 見つかればTeam、なければOptional.empty()
     */
    public Optional<Team> findById(Integer id) {
        String sql = """
                SELECT id, league_name, team_name, headquarters, inauguration, history
                FROM teams
                WHERE id = :id
                """;
        MapSqlParameterSource param = new MapSqlParameterSource("id", id);

        List<Team> list = template.query(sql, param, TEAM_ROW_MAPPER);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
