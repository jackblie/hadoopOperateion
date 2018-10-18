package conf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;

public class ConfigConsts {


    /**
     * 连接hbase的配置
     */
    private static  Configuration hbaseConf ;

    /**
     * 连接hbase的表名称
     */
    private static final String tableName = "md5table";

    /**
     * 连接的表
     */
    private static HTable table;

    static {
        try {

        hbaseConf = HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum", "hadoop-nn,hadoop-snn,hadoop-dn-01,hadoop-dn-02,hadoop-dn-03");
        hbaseConf.set("hbase.zookeeper.property.clientPort", "2181");
        hbaseConf.set("hbase.master", "hdfs://hadoop-nn:16000");
        hbaseConf.set("hbase.root.dir", "hdfs://hadoop-nn:8020/hbase");

        table = new HTable(hbaseConf,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getHbaseConf() {
        return hbaseConf;
    }

    public static String getTableName() {
        return tableName;
    }

    public static HTable getTable() {
        return table;
    }

}
