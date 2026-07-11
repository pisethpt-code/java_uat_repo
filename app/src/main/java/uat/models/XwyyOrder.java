package uat.models;

import java.math.BigDecimal;

public class XwyyOrder {

    private BigDecimal id;
    private String lineNo;
    private String recipeName;
    private String recipeType;
    private String recipeVersion;
    private BigDecimal mSetCount;
    private String mLotNo;
    private String changeTime;
    private BigDecimal isRead;
    private String mesOrder;
    private String simpleCode;

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getRecipeVersion() {
        return recipeVersion;
    }

    public void setRecipeVersion(String recipeVersion) {
        this.recipeVersion = recipeVersion;
    }

    public BigDecimal getmSetCount() {
        return mSetCount;
    }

    public void setmSetCount(BigDecimal mSetCount) {
        this.mSetCount = mSetCount;
    }

    public String getmLotNo() {
        return mLotNo;
    }

    public void setmLotNo(String mLotNo) {
        this.mLotNo = mLotNo;
    }

    public BigDecimal getIsRead() {
        return isRead;
    }

    public void setIsRead(BigDecimal isRead) {
        this.isRead = isRead;
    }

    public String getMesOrder() {
        return mesOrder;
    }

    public void setMesOrder(String mesOrder) {
        this.mesOrder = mesOrder;
    }

    public String getSimpleCode() {
        return simpleCode;
    }

    public void setSimpleCode(String simpleCode) {
        this.simpleCode = simpleCode;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }
}