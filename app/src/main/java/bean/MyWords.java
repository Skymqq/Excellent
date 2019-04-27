package bean;

import java.util.List;

public class MyWords {


    /**
     * error_code : 0
     * reason : success
     * result : [{"name":"700字","id":14},{"name":"1200字以上","id":20},{"name":"1200字","id":19},{"name":"300字","id":6},{"name":"200字","id":4},{"name":"600字","id":12},{"name":"100字","id":2},{"name":"500字","id":10},{"name":"1000字","id":18},{"name":"800字","id":16},{"name":"400字","id":8}]
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
         * name : 700字
         * id : 14
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
