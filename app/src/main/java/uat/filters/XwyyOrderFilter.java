package uat.filters;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "XwyyOrderFilter")
public class XwyyOrderFilter {
    private BigDecimal id;
    private String lineNo;
    private String recipeName;
    private String recipeType;
    private String recipeVersion;
    private BigDecimal mSetCount;
    private String mLotNo;
    private String changeTime; // date only e.g. 2023-08-15
    private String readTime; // date only e.g. 2023-08-15
    private BigDecimal isRead;
    private String mesOrder;
    private String simplecode;
    
    public XwyyOrderFilter(BigDecimal id, String lineNo, String recipeName, String recipeType, String recipeVersion,
            BigDecimal mSetCount, String mLotNo, String changeTime, String readTime, BigDecimal isRead, String mesOrder,
            String simplecode) {
        this.id = id;
        this.lineNo = lineNo;
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.recipeVersion = recipeVersion;
        this.mSetCount = mSetCount;
        this.mLotNo = mLotNo;
        this.changeTime = changeTime;
        this.readTime = readTime;
        this.isRead = isRead;
        this.mesOrder = mesOrder;
        this.simplecode = simplecode;
    }

    public XwyyOrderFilter() {
        super();
    }

    // toString xml representation
    public String toXmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XwyyOrderFilter>");
        sb.append("<id>").append(id != null ? id.toString() : "").append("</id>");
        sb.append("<lineNo>").append(lineNo != null ? lineNo : "").append("</lineNo>");
        sb.append("<recipeName>").append(recipeName != null ? recipeName : "").append("</recipeName>");
        sb.append("<recipeType>").append(recipeType != null ? recipeType : "").append("</recipeType>");
        sb.append("<recipeVersion>").append(recipeVersion != null ? recipeVersion : "").append("</recipeVersion>");
        sb.append("<mSetCount>").append(mSetCount != null ? mSetCount.toString() : "").append("</mSetCount>");
        sb.append("<mLotNo>").append(mLotNo != null ? mLotNo : "").append("</mLotNo>");
        sb.append("<changeTime>").append(changeTime != null ? changeTime : "").append("</changeTime>");
        sb.append("<readTime>").append(readTime != null ? readTime : "").append("</readTime>");
        sb.append("<isRead>").append(isRead != null ? isRead.toString() : "").append("</isRead>");
        sb.append("<mesOrder>").append(mesOrder != null ? mesOrder : "").append("</mesOrder>");
        sb.append("<simplecode>").append(simplecode != null ? simplecode : "").append("</simplecode>");
        sb.append("</XwyyOrderFilter>");
        return sb.toString();
    }

    public String getReadTime() {
        return readTime;
    }
    public void setReadTime(String readTime) {
        this.readTime = readTime;
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
    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
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
    public String getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
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
    public String getSimplecode() {
        return simplecode;
    }
    public void setSimplecode(String simplecode) {
        this.simplecode = simplecode;
    }
}
