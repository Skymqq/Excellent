package bean;

import java.util.List;

public class MyType {

    /**
     * error_code : 0
     * reason : success
     * result : [{"name":"看图","id":34},{"name":"游记","id":31},{"name":"叙事","id":12},{"name":"其他","id":40},{"name":"状物","id":14},{"name":"诗歌","id":29},{"name":"写人","id":11},{"name":"写景","id":13},{"name":"童话","id":25},{"name":"散文","id":26},{"name":"议论文","id":15},{"name":"读后感","id":21},{"name":"日记","id":18},{"name":"寓言","id":28},{"name":"说明文","id":16},{"name":"读书笔记","id":32},{"name":"话题","id":36},{"name":"想象","id":35},{"name":"演讲稿","id":22},{"name":"应用文","id":50},{"name":"书信","id":17},{"name":"小说","id":24}]
     */

    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 看图
         * id : 34
         */

        private String name;
        private int id;

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
    }
}
