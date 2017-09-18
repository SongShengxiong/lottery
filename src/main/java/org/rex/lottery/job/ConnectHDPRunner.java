package org.rex.lottery.job;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

/**
 * Created by RexSong on 2017/9/17.
 */
//@Component
public class ConnectHDPRunner implements CommandLineRunner {
    @Autowired
    private FsShell fsShell;

    @Autowired
    private org.apache.hadoop.conf.Configuration configuration;

    @Value("${lottery.hdfs.basepath}")
    private String basePath;

    @Override
    public void run(String... args) throws Exception {
        Path path = new Path(basePath + "2017-09-18");
        FileSystem fs = path.getFileSystem(configuration);
        OutputStream os = fs.create(path, false);
        os.write("rexsong".getBytes());
        os.close();
        System.out.println(fs);
    }
}
