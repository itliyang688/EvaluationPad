package cn.fek12.evaluation.model.config;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.config
 * @ClassName: Configs
 * @Description:
 * @CreateDate: 2019/12/18 14:00
 */
public class Configs {
    public static final String WEB_BASE_URL = "http://218.245.6.132:11111/html/";
    //public static final String WEB_BASE_URL = "http://192.168.0.46/noc/html/";
    public static final String INDEX = WEB_BASE_URL + "index.html?";//答题
    public static final String ANALYZE = WEB_BASE_URL + "analyze.html?";//解析
    public static final String SMALLWORK = WEB_BASE_URL + "SmallWork.html?";//小试牛刀
    public static final String RECORD = WEB_BASE_URL + "Record.html?";//错题本
    public static final String SMALL = WEB_BASE_URL + "Small.html?";//查看解析
    public static final String WRONGRECORD = WEB_BASE_URL + "WrongRecord.html";//查看解析
    public static final String ERRORREWORK = WEB_BASE_URL + "ErrorRework.html";//错题重做
}
