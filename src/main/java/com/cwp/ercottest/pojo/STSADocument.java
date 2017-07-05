package com.cwp.ercottest.pojo;

import java.util.Date;

public class STSADocument {

    private Date uploadDate;
    private String href;

    public STSADocument(Date uploadDate, String href) {
        this.uploadDate = uploadDate;
        this.href = href;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
