package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Le champ name doit être renseigner")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Le champ description doit être renseigner")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Le champ json doit être renseigner")
    @Column(name = "json")
    private String json;

    @NotBlank(message = "Le champ template doit être renseigner")
    @Column(name = "template")
    private String template;

    @NotBlank(message = "Le champ sqlStr doit être renseigner")
    @Column(name = "sqlStr")
    private String sqlStr;

    @NotBlank(message = "Le champ sqlPart doit être renseigner")
    @Column(name = "sqlPart")
    private String sqlPart;

    public RuleName() {
        super();
    }

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
