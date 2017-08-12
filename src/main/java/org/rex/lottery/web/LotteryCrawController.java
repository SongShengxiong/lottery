package org.rex.lottery.web;

import org.rex.lottery.service.DocumentParseService;
import org.rex.lottery.util.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by RexSong on 2017/8/9.
 */
@RestController("/craw")
public class LotteryCrawController {

    @Autowired
    private DocumentParseService documentParseService;

    @RequestMapping("/ssq")
    @ResponseBody
    public String crawSSQ(@RequestParam String type, @RequestParam String url) {
        Lottery lottery = Lottery.typeOf(type);
        if (lottery == null || url == null) {
            return "type and url is needed";
        }

        return documentParseService.parseAndSave(url, lottery) ? "Success" : "Failed";
    }
}
