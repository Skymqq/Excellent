package bean;

import java.util.List;

//年级
public class MyClass {
    /**
     * error_code : 0
     * reason : success
     * result : [{"name":"小升初","id":17},{"name":"六年级","id":16},{"name":"三年级","id":13},{"name":"五年级","id":15},{"name":"初三","id":23},{"name":"高考","id":34},{"name":"一年级","id":11},{"name":"初一","id":21},{"name":"中考","id":24},{"name":"初二","id":22},{"name":"四年级","id":14},{"name":"高二","id":32},{"name":"高一","id":31},{"name":"高三","id":33},{"name":"二年级","id":12}]
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
         * name : 小升初
         * id : 17
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
