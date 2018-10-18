package md5ToMob;

import conf.CommonUtils;
import conf.ConfigConsts;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GetMobile {
    public static void main(String[] args) {

//        String read_path = args[0];
//        String write_path = args[1];

        // 读取文件和写入文件
        String read_path = "D:\\input_md5.txt";
        String write_path = "D:\\new-input_md5.txt";

        // 获取连接hbase的连接的客户端
        HTable table = ConfigConsts.getTable();

        try {
            BufferedReader read = CommonUtils.readFile(read_path);
            FileWriter writer = CommonUtils.writeFile(write_path);

            // 设置批量请求的容器并且把读取的数据写入
            List<Get> getList = new ArrayList<>();
            String temp = null;
            while ((temp = read.readLine()) != null){
                String str = temp.toUpperCase();
                Get get = new Get(Bytes.toBytes(str));
                getList.add(get);
            }

            // 批量查询并获取结果
            Result[] results = table.get(getList);
            int count = 0;
            for (Result result : results){
                count++;
                String mobile = "error";
                if (!result.isEmpty()){
                    // 选取第一版本的数据
                    for (Cell kv : result.rawCells()){
                        mobile = Bytes.toString(CellUtil.cloneValue(kv));
                        break;
                    }
                }
                writer.write(mobile+"\n");
            }

            System.out.println("成功"+count);
            table.close();
            writer.close();
            read.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
