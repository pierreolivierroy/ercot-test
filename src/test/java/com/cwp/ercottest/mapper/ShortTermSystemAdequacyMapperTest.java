package com.cwp.ercottest.mapper;

import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortTermSystemAdequacyMapperTest {

    private ShortTermSystemAdequacyMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ShortTermSystemAdequacyMapper();
    }

    @Test
    public void map() throws Exception {

        ShortTermSystemAdequacy shortTermSystemAdequacy = mapper.map(new DateTime(2017, 7, 1, 0, 0).toDate(), "06/26/2017,01:00,49414.9,2519,0,N");

        assertThat(shortTermSystemAdequacy).isNotNull();
    }
}