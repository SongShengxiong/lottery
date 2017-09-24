package org.rex.lottery.dao;

import com.google.common.base.Preconditions;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.rex.lottery.bean.SSQInfoDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by RexSong on 2017/9/18.
 */
@Service("lotterySSQInfoDetailDAO")
public class LotterySSQInfoDetailDAO implements ILotterySSQInfoDetailDAO {
    private static final Logger logger = LoggerFactory.getLogger(LotterySSQInfoDetailDAO.class);

    @Autowired
    private FsShell fsShell;

    @Autowired
    private org.apache.hadoop.conf.Configuration configuration;

    @Value("${lottery.hdfs.basepath}")
    private String basePath;

    @Override
    public boolean exists(String dayOfYYYYMMDD) {
        return fsShell.test(basePath + dayOfYYYYMMDD);
    }

    @Override
    public SSQInfoDetail getOne(String dayOfYYYYMMDD) {

        String content = null;

        try {
            Collection<String> data = fsShell.text(basePath + dayOfYYYYMMDD);
            Preconditions.checkNotNull(data);
            List<String> listData = new ArrayList<String>(data);
            Preconditions.checkNotNull(listData);
            content = listData.get(0);
            Preconditions.checkNotNull(content);

        } catch (IllegalArgumentException iae) {
            return null;
        }

        return SSQInfoDetail.deserialize(content);
    }

    @Override
    public boolean addOne(SSQInfoDetail ssqInfoDetail) {
        String content = ssqInfoDetail.serialize();
        Preconditions.checkNotNull(content);
        logger.info("adding SSQInfoDetail {}", ssqInfoDetail);
        if (_add2HDFS(basePath + ssqInfoDetail.getDate(), content)) return false;
        return true;
    }

    private boolean _add2HDFS(String uri, String content) {
        OutputStream os = null;
        try {
            Path path = new Path(uri);
            FileSystem fs = path.getFileSystem(configuration);
            os = fs.create(path, false);
            os.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean addList(List<SSQInfoDetail> ssqInfoDetailList) {
        ssqInfoDetailList.stream().forEach( ssqInfoDetail -> addOne(ssqInfoDetail));
        return true;
    }
}
