package com.cwp.ercottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImportController {

    @RequestMapping("/import")
    public String importReportFromDate() {
        return "importReportFromDate";
    }
}
