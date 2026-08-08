package uat.models;

import java.math.BigDecimal;

public class XwyyOutput {
    private BigDecimal Id;
    private String LineNo;
    private String RecipeName;
    private String RecipeType;
    private BigDecimal Qty;
    private String ProductTime;
    private BigDecimal IsRead;
    private String ReadTime;
    private String OrderNo;
    private String SublotNo;
    
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
    public String getRecipeName() {
        return RecipeName;
    }
    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }
    public String getRecipeType() {
        return RecipeType;
    }
    public void setRecipeType(String recipeType) {
        RecipeType = recipeType;
    }
    public BigDecimal getQty() {
        return Qty;
    }
    public void setQty(BigDecimal qty) {
        Qty = qty;
    }
    public String getProductTime() {
        return ProductTime;
    }
    public void setProductTime(String productTime) {
        ProductTime = productTime;
    }
    public BigDecimal getIsRead() {
        return IsRead;
    }
    public void setIsRead(BigDecimal isRead) {
        IsRead = isRead;
    }
    public String getReadTime() {
        return ReadTime;
    }
    public void setReadTime(String readTime) {
        ReadTime = readTime;
    }
    public String getOrderNo() {
        return OrderNo;
    }
    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }
    public String getSublotNo() {
        return SublotNo;
    }
    public void setSublotNo(String sublotNo) {
        SublotNo = sublotNo;
    }
}
