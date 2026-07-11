package uat.models;

import java.math.BigDecimal;

public class XwyyOrder {

    private BigDecimal Id;
    private String LineNo;
    private String RecipeName;
    private String RecipeType;
    private String RecipeVersion;
    private BigDecimal MSetCount;
    private String MLotNo;
    private BigDecimal IsRead;
    private String MesOrder;
    private String SimpleCode;    

    public String getRecipeType() {
        return RecipeType;
    }

    public void setRecipeType(String recipeType) {
        RecipeType = recipeType;
    }

    public String getRecipeVersion() {
        return RecipeVersion;
    }

    public void setRecipeVersion(String recipeVersion) {
        RecipeVersion = recipeVersion;
    }

    public BigDecimal getMSetCount() {
        return MSetCount;
    }

    public void setMSetCount(BigDecimal mSetCount) {
        MSetCount = mSetCount;
    }

    public String getMLotNo() {
        return MLotNo;
    }

    public void setMLotNo(String mLotNo) {
        MLotNo = mLotNo;
    }

    public BigDecimal getIsRead() {
        return IsRead;
    }

    public void setIsRead(BigDecimal isRead) {
        IsRead = isRead;
    }

    public String getMesOrder() {
        return MesOrder;
    }

    public void setMesOrder(String mesOrder) {
        MesOrder = mesOrder;
    }

    public String getSimpleCode() {
        return SimpleCode;
    }

    public void setSimpleCode(String simpleCode) {
        SimpleCode = simpleCode;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }

    public String getLineNo() {
        return LineNo;
    }

    public void setLineNo(String lineNo) {
        LineNo = lineNo;
    }

    public void setRecipeName(String recipeName){
        RecipeName = recipeName;
    }

    public String getRecipeName() {
        return RecipeName;
    }

}