package org.rex.lottery.web

import org.rex.lottery.bean.RestResponse
import org.rex.lottery.bean.SSQInfoDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by RexSong on 2017/9/24.
 */
@RestController
@RequestMapping("/ssq")
class SSQControllerKt {
    val logger: Logger= LoggerFactory.getLogger(SSQControllerKt::class.java)

    @Autowired
    lateinit var cassandraTemplate : CassandraTemplate

    @PutMapping(value = "/{period}/infodetails")
    @ResponseBody
    fun addManually(@PathVariable("period") period : String,
                                    @RequestBody  ssqInfoDetail : SSQInfoDetail) : RestResponse<SSQInfoDetail> {
        if (cassandraTemplate.exists(SSQInfoDetail::class.java, period)) {
            logger.warn("already exists {}", period)
            return RestResponse("0000", "success", ssqInfoDetail)
        }
        ssqInfoDetail.period = period
        return RestResponse("0000", "success", cassandraTemplate.insert(ssqInfoDetail))
    }

}