package conf;

import java.io.*;


public class CommonUtils {


    /**
     *
     * @param path 读取文件的路径
     * @return
     */
    public static BufferedReader readFile(String path) throws FileNotFoundException {

        FileInputStream file = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        return reader;
    }

    /**
     *
     * @param path 写入文件的路径
     * @return
     */
    public static FileWriter writeFile(String path) throws IOException {
        FileWriter writer = new FileWriter(path,false);
        return writer;
    }
}
