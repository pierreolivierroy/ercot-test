package com.cwp.ercottest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ercot_short_terms_system_adequacy_report")
public class ShortTermSystemAdequacy {

    @Id
    @Column(name = "originaldatetime")
    private Timestamp originalDateTime;

    @Column(name = "originalrefreshdatetime")
    private Timestamp originalRefreshDatetime;

    @Column(name = "localdatetime")
    private Timestamp localDateTime;

    @Column(name = "total_cap_gen_res")
    private BigDecimal totalCapGenRRes;

    @Column(name = "total_cap_load_res")
    private BigDecimal totalCapLoadRes;

    @Column(name = "offline_available_mw")
    private BigDecimal offlineAvailableMW;

    public Timestamp getOriginalDateTime() {
        return originalDateTime;
    }

    public void setOriginalDateTime(Timestamp originalDateTime) {
        this.originalDateTime = originalDateTime;
    }

    public Timestamp getOriginalRefreshDatetime() {
        return originalRefreshDatetime;
    }

    public void setOriginalRefreshDatetime(Timestamp originalRefreshDatetime) {
        this.originalRefreshDatetime = originalRefreshDatetime;
    }

    public Timestamp getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(Timestamp localDateTime) {
        this.localDateTime = localDateTime;
    }

    public BigDecimal getTotalCapGenRRes() {
        return totalCapGenRRes;
    }

    public void setTotalCapGenRRes(BigDecimal totalCapGenRRes) {
        this.totalCapGenRRes = totalCapGenRRes;
    }

    public BigDecimal getTotalCapLoadRes() {
        return totalCapLoadRes;
    }

    public void setTotalCapLoadRes(BigDecimal totalCapLoadRes) {
        this.totalCapLoadRes = totalCapLoadRes;
    }

    public BigDecimal getOfflineAvailableMW() {
        return offlineAvailableMW;
    }

    public void setOfflineAvailableMW(BigDecimal offlineAvailableMW) {
        this.offlineAvailableMW = offlineAvailableMW;
    }

    //    originaldatetime timestamp without time zone NOT NULL, -- Using CPT Time Zone
//    originalrefreshdatetime timestamp without timezone NOT NULL, --UsingCPTTimeZone
//    localdatetime timestamp with time zone NOT NULL, -- Using EPT Time Zone
//    total_cap_gen_res numeric,
//    total_cap_load_res numeric,
//    offline_available_mw numeric,
//    CONSTRAINT pk_ercot_short_terms_system_adequacy_report PRIMARY KEY (originaldatetime)
}
