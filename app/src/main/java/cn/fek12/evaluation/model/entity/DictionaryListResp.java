package cn.fek12.evaluation.model.entity;

import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: DictionaryListResp
 * @Description:
 * @CreateDate: 2019/10/25 13:17
 */
public class DictionaryListResp {


    /**
     * data : [{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"一年级","id":1},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"二年级","id":2},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"三年级","id":3},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"四年级","id":4},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"五年级","id":5},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"六年级","id":6},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"七年级","id":7},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"八年级","id":8},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]},"name":"九年级","id":9},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"部编版","id":"63"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"},{"name":"物理","id":"31"}],"semester":[{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"}]},"name":"高一年级","id":10},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"部编版","id":"63"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"},{"name":"物理","id":"31"}],"semester":[{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"}]},"name":"高二年级","id":11},{"tabInfo":{"textbook":[{"name":"人教版","id":"18"},{"name":"部编版","id":"63"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"},{"name":"历史","id":"26"},{"name":"政治","id":"27"},{"name":"地理","id":"28"},{"name":"生物","id":"29"},{"name":"化学","id":"30"},{"name":"物理","id":"31"}],"semester":[{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"},{"name":"必修一","id":"81"}]},"name":"高三年级","id":12}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<DataBean> data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tabInfo : {"textbook":[{"name":"人教版","id":"18"},{"name":"北师大版","id":"64"}],"subject":[{"name":"语文","id":"13"},{"name":"数学","id":"14"},{"name":"英语","id":"15"}],"semester":[{"name":"上学期","id":"16"},{"name":"下学期","id":"17"}]}
         * name : 一年级
         * id : 1
         */

        private TabInfoBean tabInfo;
        private String name;
        private int id;

        public TabInfoBean getTabInfo() {
            return tabInfo;
        }

        public void setTabInfo(TabInfoBean tabInfo) {
            this.tabInfo = tabInfo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class TabInfoBean {
            private List<SubTabInfo> textbook;
            private List<SubTabInfo> subject;
            private List<SubTabInfo> semester;

            public List<SubTabInfo> getTextbook() {
                return textbook;
            }

            public void setTextbook(List<SubTabInfo> textbook) {
                this.textbook = textbook;
            }

            public List<SubTabInfo> getSubject() {
                return subject;
            }

            public void setSubject(List<SubTabInfo> subject) {
                this.subject = subject;
            }

            public List<SubTabInfo> getSemester() {
                return semester;
            }

            public void setSemester(List<SubTabInfo> semester) {
                this.semester = semester;
            }

            public static class SubTabInfo {
                /**
                 * name : 人教版
                 * id : 18
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
