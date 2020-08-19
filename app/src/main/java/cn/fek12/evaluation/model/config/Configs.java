package cn.fek12.evaluation.model.config;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.config
 * @ClassName: Configs
 * @Description:
 * @CreateDate: 2019/12/18 14:00
 */
public class Configs {
    //public static final String WEB_BASE_URL = "http://218.245.6.132:11111/";
    //public static final String WEB_BASE_URL = "http://218.245.6.132:11113/";
    //public static final String WEB_BASE_URL = "http://121.37.254.233:8080/";
    //public static final String WEB_BASE_URL = "http://api.51jxpj.cn/";
    public static final String WEB_BASE_URL = "http://192.168.0.46/noc/";
    //public static final String WEB_BASE_URL1 = "http://192.168.0.77:11111/";
    public static final String INDEX = WEB_BASE_URL + "html/index.html?";//答题
    public static final String ANALYZE = WEB_BASE_URL + "html/analyze.html?";//解析
    public static final String SMALLWORK = WEB_BASE_URL + "html/SmallWork.html?";//小试牛刀
    public static final String RECORD = WEB_BASE_URL + "html/Record.html?";//错题本
    public static final String SMALL = WEB_BASE_URL + "html/Small.html?";//查看解析
    public static final String WRONGRECORD = WEB_BASE_URL + "html/WrongRecord.html";//查看解析
    public static final String ERRORREWORK = WEB_BASE_URL + "html/ErrorRework.html";//错题重做
    public static final String PERSONAL_REPORT = WEB_BASE_URL + "accurateReport/report?";//个人报告
}
