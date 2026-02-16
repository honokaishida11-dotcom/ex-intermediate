package com.example.exintermediate.domain;

/**
 * 
 * @author Ishida
 * teamsテーブルの1レコード（1行）を表すドメインクラス。
 *
 * <p>DBの列（league_nameなど）をJavaで扱いやすい形（camelCase）にして持たせる。</p>
 */
public class Team {

    /** 主キー（自動採番） */
    private Integer id;

    /** リーグ名（例：セントラル・リーグ） */
    private String leagueName;

    /** チーム名（例：読売ジャイアンツ） */
    private String teamName;

    /** 本拠地（例：東京ドーム） */
    private String headquarters;

    /**
     * 発足日。
     * <p>DBでは text で保存されている（例：1934年12月26日）</p>
     */
    private String inauguration;

    /**
     * 歴史
     * 
     */
    private String history;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLeagueName() { return leagueName; }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getHeadquarters() { return headquarters; }
    public void setHeadquarters(String headquarters) { this.headquarters = headquarters; }

    public String getInauguration() { return inauguration; }
    public void setInauguration(String inauguration) { this.inauguration = inauguration; }

    public String getHistory() { return history; }
    public void setHistory(String history) { this.history = history; }
}
