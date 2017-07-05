package com.cwp.ercottest.mapper;

import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShortTermSystemAdequacyMapper {

    public ShortTermSystemAdequacy map(Date uploadDate, String element) {

        try {

            if (StringUtils.isNotBlank(element)) {
                String[] split = element.split(",");
                ShortTermSystemAdequacy shortTermSystemAdequacy = new ShortTermSystemAdequacy();
                Date originalDateTime = getOriginalDateTime(split[0] + split[1]);

                shortTermSystemAdequacy.setOriginalDateTime(new Timestamp(originalDateTime.getTime()));
                shortTermSystemAdequacy.setOriginalRefreshDatetime(new Timestamp(uploadDate.getTime()));
                shortTermSystemAdequacy.setLocalDateTime(new Timestamp(DateUtils.addHours(originalDateTime, 1).getTime()));
                shortTermSystemAdequacy.setTotalCapGenRRes(new BigDecimal(split[2]));
                shortTermSystemAdequacy.setTotalCapLoadRes(new BigDecimal(split[3]));
                shortTermSystemAdequacy.setOfflineAvailableMW(new BigDecimal(split[4]));

                return shortTermSystemAdequacy;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ShortTermSystemAdequacy> mapList(Date uploadDate, List<String> fileContent) {

        if (CollectionUtils.isEmpty(fileContent) || uploadDate == null) {
            Collections.emptyList();
        }

        return fileContent.stream()
                .map(element -> map(uploadDate, element))
                .collect(Collectors.toList());
    }

    private Date getOriginalDateTime(String date) throws ParseException {

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyyhh:mm");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
