package com.hbtcsrzzx.ssm.po.queryVo;

/**
 * 返利信息包装的实体类
 */
public class Royalty {
    private String name;
    private Integer  cost;
    private Integer returnRatio;
    private String distributionLevel;
    private String recommenderLevel;

    public String getDistributionLevel() {
        return distributionLevel;
    }

    public void setDistributionLevel(String distributionLevel) {
        this.distributionLevel = distributionLevel;
    }

    public String getRecommenderLevel() {
        return recommenderLevel;
    }

    public void setRecommenderLevel(String recommenderLevel) {
        this.recommenderLevel = recommenderLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getReturnRatio() {
        return returnRatio;
    }

    public void setReturnRatio(Integer returnRatio) {
        this.returnRatio = returnRatio;
    }
}
