package cn.fek12.evaluation.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ProtoCommon;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class FastDFSUtil {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {
        System.out.println(generateSourceUrl("http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4"));
    }

    /**
     * 生成防盗链token
     * @param url  http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws MyException
     */
    public static String generateSourceUrl(String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {
        if (url == null) {
            return null;
        }
        //时间戳
        int lts = (int)(System.currentTimeMillis() / 1000);
        String[] split = url.split("/");
        if (split.length < 8) {
            return null;
        }
        //拼接域名
        String httpHost = split[0]+"/"+split[1]+"/"+split[2];
        //拼接文件地址
        String remoteFilePath = split[4]+"/"+split[5]+"/"+split[6]+"/"+split[7];
        //生成token
        String token = ProtoCommon.getToken(remoteFilePath, lts, "BAW#ZgmG$L1SExzE");
        return httpHost + "/group1/" + remoteFilePath + "?token=" + token + "&ts=" + lts;
    }
}
