package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: TreeDataEntity
 * @Description:
 * @CreateDate: 2019/10/29 18:12
 */
public class TreeDataEntity implements Serializable {


    /**
     * data : [{"name":"测试","orderList":1,"id":55127,"childs":[{"name":"测试课节第一章","orderList":1,"id":55128,"childs":[{"name":"测试课节第一节","orderList":1,"id":55129,"childs":[{"name":"测试课节第一个知识点","orderList":2,"id":55130,"parentId":55129}],"parentId":55128}],"parentId":55127}]}]
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
         * name : 测试
         * orderList : 1
         * id : 55127
         * childs : [{"name":"测试课节第一章","orderList":1,"id":55128,"childs":[{"name":"测试课节第一节","orderList":1,"id":55129,"childs":[{"name":"测试课节第一个知识点","orderList":2,"id":55130,"parentId":55129}],"parentId":55128}],"parentId":55127}]
         */

        private String name;
        private int orderList;
        private int id;
        private List<ChildsBeanXX> childs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderList() {
            return orderList;
        }

        public void setOrderList(int orderList) {
            this.orderList = orderList;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ChildsBeanXX> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBeanXX> childs) {
            this.childs = childs;
        }

        public static class ChildsBeanXX {
            /**
             * name : 测试课节第一章
             * orderList : 1
             * id : 55128
             * childs : [{"name":"测试课节第一节","orderList":1,"id":55129,"childs":[{"name":"测试课节第一个知识点","orderList":2,"id":55130,"parentId":55129}],"parentId":55128}]
             * parentId : 55127
             */

            private String name;
            private int orderList;
            private int id;
            private int parentId;
            private List<ChildsBeanX> childs;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrderList() {
                return orderList;
            }

            public void setOrderList(int orderList) {
                this.orderList = orderList;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public List<ChildsBeanX> getChilds() {
                return childs;
            }

            public void setChilds(List<ChildsBeanX> childs) {
                this.childs = childs;
            }

            public static class ChildsBeanX {
                /**
                 * name : 测试课节第一节
                 * orderList : 1
                 * id : 55129
                 * childs : [{"name":"测试课节第一个知识点","orderList":2,"id":55130,"parentId":55129}]
                 * parentId : 55128
                 */

                private String name;
                private int orderList;
                private int id;
                private int parentId;
                private List<ChildsBean> childs;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getOrderList() {
                    return orderList;
                }

                public void setOrderList(int orderList) {
                    this.orderList = orderList;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public List<ChildsBean> getChilds() {
                    return childs;
                }

                public void setChilds(List<ChildsBean> childs) {
                    this.childs = childs;
                }

                public static class ChildsBean {
                    /**
                     * name : 测试课节第一个知识点
                     * orderList : 2
                     * id : 55130
                     * parentId : 55129
                     */

                    private String name;
                    private int orderList;
                    private int id;
                    private int parentId;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getOrderList() {
                        return orderList;
                    }

                    public void setOrderList(int orderList) {
                        this.orderList = orderList;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }
                }
            }
        }
    }
}
