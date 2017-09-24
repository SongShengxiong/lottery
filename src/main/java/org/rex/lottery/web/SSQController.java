package org.rex.lottery.web;

/**
 * Created by RexSong on 2017/9/24.
 */
//@RestController
//@RequestMapping("/ssq")
//public class SSQController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private CassandraTemplate cassandraTemplate;
//
//    @RequestMapping(value = "/{period}/infodetails", method = RequestMethod.PUT)
//    @ResponseBody
//    public RestResponse addManually(@PathVariable("period") String period,
//                                    @RequestBody SSQInfoDetail ssqInfoDetail) {
//        if (cassandraTemplate.exists(SSQInfoDetail.class, period)) {
//            logger.warn("already exists {}", period);
//            return new RestResponse("1000", "already exists", ssqInfoDetail);
//        }
//        ssqInfoDetail.setPeriod(period);
//        return new RestResponse("0000", "success", cassandraTemplate.insert(ssqInfoDetail));
//    }
//
//}
