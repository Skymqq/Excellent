package bean;

import java.util.List;

public class Article {

    /**
     * error_code : 0
     * reason : success
     * result : {"totalCount":22257,"page":1,"size":50,"list":[{"gradeId":12,"level":2,"name":"可爱的秋天","time":"2014-03-24","typeId":13,"wordId":4,"writer":"高豆豆","id":343811},{"gradeId":12,"level":3,"name":"爬山","time":"2014-10-11","typeId":12,"wordId":6,"writer":"叶奕婷","id":29548},{"gradeId":12,"level":2,"name":"老师,我想对您说","time":"2014-02-13","typeId":12,"wordId":6,"writer":"高豆豆","id":345731},{"gradeId":12,"level":2,"name":"文具盒的自述","time":"2013-02-12","typeId":14,"wordId":6,"writer":"乱世飞花","id":506940},{"gradeId":12,"level":2,"name":"牙掉了","time":"2013-03-18","typeId":18,"wordId":6,"writer":"陈茜园","id":28961},{"gradeId":12,"level":2,"name":"闯红灯","time":"2013-08-17","typeId":18,"wordId":4,"writer":"刘忠健","id":28716},{"gradeId":12,"level":2,"name":"美丽的春天","time":"2014-05-31","typeId":13,"wordId":6,"writer":"高豆豆","id":443386},{"gradeId":12,"level":2,"name":"春天多美丽","time":"2013-11-04","typeId":13,"wordId":8,"writer":"高豆豆","id":458584},{"gradeId":12,"level":2,"name":"我爱二．一班","time":"2013-06-18","typeId":12,"wordId":8,"writer":"高豆豆","id":491020},{"gradeId":12,"level":2,"name":"不高兴的爸爸妈妈","time":"2013-10-26","typeId":12,"wordId":6,"writer":"高豆豆","id":382953},{"gradeId":12,"level":2,"name":"怕苦怕累的螺丝钉","time":"2014-08-27","typeId":18,"wordId":6,"writer":"高豆豆","id":397599},{"gradeId":12,"level":2,"name":"秋天","time":"2014-06-13","typeId":13,"wordId":4,"writer":"高豆豆","id":312081},{"gradeId":12,"level":2,"name":"寻找春天","time":"2014-03-11","typeId":13,"wordId":6,"writer":"谢百强","id":13649},{"gradeId":12,"level":2,"name":"年桔","time":"2013-07-23","typeId":14,"wordId":6,"writer":"高豆豆","id":67115},{"gradeId":12,"level":2,"name":"运木头比赛","time":"2014-03-03","typeId":18,"wordId":6,"writer":"高豆豆","id":396658},{"gradeId":12,"level":2,"name":"爬铁牛","time":"2014-11-11","typeId":18,"wordId":4,"writer":"高豆豆","id":396697},{"gradeId":12,"level":2,"name":"打枣子","time":"2014-12-02","typeId":12,"wordId":8,"writer":"阳弢颖","id":19705},{"gradeId":12,"level":2,"name":"春天来了","time":"2013-07-20","typeId":13,"wordId":4,"writer":"高豆豆","id":458778},{"gradeId":12,"level":2,"name":"开心的\u201c六一\u201d节","time":"2014-04-10","typeId":12,"wordId":6,"writer":"高豆豆","id":416109},{"gradeId":12,"level":3,"name":"梦","time":"2013-07-07","typeId":35,"wordId":10,"writer":"高豆豆","id":24092},{"gradeId":12,"level":2,"name":"我最喜欢的一张照片","time":"2013-04-07","typeId":12,"wordId":6,"writer":"邵帅","id":21078},{"gradeId":12,"level":2,"name":"消失的磨牙声","time":"2014-01-18","typeId":12,"wordId":8,"writer":"高豆豆","id":344217},{"gradeId":12,"level":2,"name":"冒险岛","time":"2013-05-30","typeId":12,"wordId":4,"writer":"张锡山","id":487295},{"gradeId":12,"level":2,"name":"小鸭","time":"2014-09-01","typeId":14,"wordId":6,"writer":"xx雄狮","id":68325},{"gradeId":12,"level":3,"name":"吃杨梅","time":"2013-07-21","typeId":12,"wordId":6,"writer":"高豆豆","id":25744},{"gradeId":12,"level":2,"name":"给杨阳的一封信","time":"2013-07-23","typeId":17,"wordId":10,"writer":"高豆豆","id":27761},{"gradeId":12,"level":2,"name":"草莓","time":"2013-07-22","typeId":14,"wordId":6,"writer":"高豆豆","id":25797},{"gradeId":12,"level":2,"name":"螳螂","time":"2014-09-06","typeId":14,"wordId":6,"writer":"高豆豆","id":340726},{"gradeId":12,"level":2,"name":"写给老师的信","time":"2014-10-18","typeId":17,"wordId":4,"writer":"高豆豆","id":341970},{"gradeId":12,"level":2,"name":"春游森林公园","time":"2013-04-03","typeId":12,"wordId":6,"writer":"admin","id":501252},{"gradeId":12,"level":3,"name":"蚂蚁大战","time":"2013-10-09","typeId":12,"wordId":6,"writer":"潘璐恬","id":20803},{"gradeId":12,"level":2,"name":"快乐元旦节","time":"2013-11-06","typeId":12,"wordId":6,"writer":"高豆豆","id":342164},{"gradeId":12,"level":2,"name":"庆祝国际劳动节","time":"2013-11-21","typeId":12,"wordId":4,"writer":"高豆豆","id":452082},{"gradeId":12,"level":2,"name":"秋天的语言","time":"2013-07-24","typeId":13,"wordId":8,"writer":"高豆豆","id":343014},{"gradeId":12,"level":2,"name":"美丽的九月菊","time":"2014-10-31","typeId":14,"wordId":6,"writer":"高豆豆","id":434453},{"gradeId":12,"level":2,"name":"学做饭","time":"2013-07-26","typeId":12,"wordId":6,"writer":"高豆豆","id":344749},{"gradeId":12,"level":2,"name":"我家的小狗","time":"2013-07-29","typeId":14,"wordId":4,"writer":"李诗怡","id":26075},{"gradeId":12,"level":2,"name":"我和爸爸下象棋","time":"2014-08-20","typeId":18,"wordId":4,"writer":"胡云翔","id":29535},{"gradeId":12,"level":2,"name":"我爱家乡的小河","time":"2013-07-25","typeId":14,"wordId":4,"writer":"高豆豆","id":436003},{"gradeId":12,"level":2,"name":"整房间","time":"2014-02-20","typeId":18,"wordId":4,"writer":"李啊","id":29402},{"gradeId":12,"level":3,"name":"我那只可爱的小鸡","time":"2013-04-06","typeId":12,"wordId":6,"writer":"刘炜烨","id":19079},{"gradeId":12,"level":2,"name":"春雨作文：春雨沙沙","time":"2014-03-07","typeId":13,"wordId":4,"writer":"高豆豆","id":295609},{"gradeId":12,"level":2,"name":"飞机的秘密","time":"2013-11-04","typeId":12,"wordId":10,"writer":"高豆豆","id":18122},{"gradeId":12,"level":2,"name":"我最喜欢的鸟","time":"2014-03-26","typeId":14,"wordId":4,"writer":"嘻哈999","id":45207},{"gradeId":12,"level":2,"name":"我的好朋友","time":"2013-02-14","typeId":14,"wordId":4,"writer":"付强","id":96615},{"gradeId":12,"level":2,"name":"我的小台灯","time":"2013-08-14","typeId":14,"wordId":4,"writer":"邱琎喆","id":26548},{"gradeId":12,"level":2,"name":"秋天的向阳山","time":"2014-09-10","typeId":13,"wordId":6,"writer":"湘灵月光","id":85058},{"gradeId":12,"level":2,"name":"郊外的春天","time":"2014-03-31","typeId":13,"wordId":4,"writer":"高豆豆","id":341479},{"gradeId":12,"level":2,"name":"美丽的枫叶","time":"2014-06-24","typeId":14,"wordId":6,"writer":"高豆豆","id":488715},{"gradeId":12,"level":2,"name":"\u201c鬼\u201d混","time":"2014-12-26","typeId":12,"wordId":6,"writer":"杜宇翔","id":48391}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * totalCount : 22257
         * page : 1
         * size : 50
         * list : [{"gradeId":12,"level":2,"name":"可爱的秋天","time":"2014-03-24","typeId":13,"wordId":4,"writer":"高豆豆","id":343811},{"gradeId":12,"level":3,"name":"爬山","time":"2014-10-11","typeId":12,"wordId":6,"writer":"叶奕婷","id":29548},{"gradeId":12,"level":2,"name":"老师,我想对您说","time":"2014-02-13","typeId":12,"wordId":6,"writer":"高豆豆","id":345731},{"gradeId":12,"level":2,"name":"文具盒的自述","time":"2013-02-12","typeId":14,"wordId":6,"writer":"乱世飞花","id":506940},{"gradeId":12,"level":2,"name":"牙掉了","time":"2013-03-18","typeId":18,"wordId":6,"writer":"陈茜园","id":28961},{"gradeId":12,"level":2,"name":"闯红灯","time":"2013-08-17","typeId":18,"wordId":4,"writer":"刘忠健","id":28716},{"gradeId":12,"level":2,"name":"美丽的春天","time":"2014-05-31","typeId":13,"wordId":6,"writer":"高豆豆","id":443386},{"gradeId":12,"level":2,"name":"春天多美丽","time":"2013-11-04","typeId":13,"wordId":8,"writer":"高豆豆","id":458584},{"gradeId":12,"level":2,"name":"我爱二．一班","time":"2013-06-18","typeId":12,"wordId":8,"writer":"高豆豆","id":491020},{"gradeId":12,"level":2,"name":"不高兴的爸爸妈妈","time":"2013-10-26","typeId":12,"wordId":6,"writer":"高豆豆","id":382953},{"gradeId":12,"level":2,"name":"怕苦怕累的螺丝钉","time":"2014-08-27","typeId":18,"wordId":6,"writer":"高豆豆","id":397599},{"gradeId":12,"level":2,"name":"秋天","time":"2014-06-13","typeId":13,"wordId":4,"writer":"高豆豆","id":312081},{"gradeId":12,"level":2,"name":"寻找春天","time":"2014-03-11","typeId":13,"wordId":6,"writer":"谢百强","id":13649},{"gradeId":12,"level":2,"name":"年桔","time":"2013-07-23","typeId":14,"wordId":6,"writer":"高豆豆","id":67115},{"gradeId":12,"level":2,"name":"运木头比赛","time":"2014-03-03","typeId":18,"wordId":6,"writer":"高豆豆","id":396658},{"gradeId":12,"level":2,"name":"爬铁牛","time":"2014-11-11","typeId":18,"wordId":4,"writer":"高豆豆","id":396697},{"gradeId":12,"level":2,"name":"打枣子","time":"2014-12-02","typeId":12,"wordId":8,"writer":"阳弢颖","id":19705},{"gradeId":12,"level":2,"name":"春天来了","time":"2013-07-20","typeId":13,"wordId":4,"writer":"高豆豆","id":458778},{"gradeId":12,"level":2,"name":"开心的\u201c六一\u201d节","time":"2014-04-10","typeId":12,"wordId":6,"writer":"高豆豆","id":416109},{"gradeId":12,"level":3,"name":"梦","time":"2013-07-07","typeId":35,"wordId":10,"writer":"高豆豆","id":24092},{"gradeId":12,"level":2,"name":"我最喜欢的一张照片","time":"2013-04-07","typeId":12,"wordId":6,"writer":"邵帅","id":21078},{"gradeId":12,"level":2,"name":"消失的磨牙声","time":"2014-01-18","typeId":12,"wordId":8,"writer":"高豆豆","id":344217},{"gradeId":12,"level":2,"name":"冒险岛","time":"2013-05-30","typeId":12,"wordId":4,"writer":"张锡山","id":487295},{"gradeId":12,"level":2,"name":"小鸭","time":"2014-09-01","typeId":14,"wordId":6,"writer":"xx雄狮","id":68325},{"gradeId":12,"level":3,"name":"吃杨梅","time":"2013-07-21","typeId":12,"wordId":6,"writer":"高豆豆","id":25744},{"gradeId":12,"level":2,"name":"给杨阳的一封信","time":"2013-07-23","typeId":17,"wordId":10,"writer":"高豆豆","id":27761},{"gradeId":12,"level":2,"name":"草莓","time":"2013-07-22","typeId":14,"wordId":6,"writer":"高豆豆","id":25797},{"gradeId":12,"level":2,"name":"螳螂","time":"2014-09-06","typeId":14,"wordId":6,"writer":"高豆豆","id":340726},{"gradeId":12,"level":2,"name":"写给老师的信","time":"2014-10-18","typeId":17,"wordId":4,"writer":"高豆豆","id":341970},{"gradeId":12,"level":2,"name":"春游森林公园","time":"2013-04-03","typeId":12,"wordId":6,"writer":"admin","id":501252},{"gradeId":12,"level":3,"name":"蚂蚁大战","time":"2013-10-09","typeId":12,"wordId":6,"writer":"潘璐恬","id":20803},{"gradeId":12,"level":2,"name":"快乐元旦节","time":"2013-11-06","typeId":12,"wordId":6,"writer":"高豆豆","id":342164},{"gradeId":12,"level":2,"name":"庆祝国际劳动节","time":"2013-11-21","typeId":12,"wordId":4,"writer":"高豆豆","id":452082},{"gradeId":12,"level":2,"name":"秋天的语言","time":"2013-07-24","typeId":13,"wordId":8,"writer":"高豆豆","id":343014},{"gradeId":12,"level":2,"name":"美丽的九月菊","time":"2014-10-31","typeId":14,"wordId":6,"writer":"高豆豆","id":434453},{"gradeId":12,"level":2,"name":"学做饭","time":"2013-07-26","typeId":12,"wordId":6,"writer":"高豆豆","id":344749},{"gradeId":12,"level":2,"name":"我家的小狗","time":"2013-07-29","typeId":14,"wordId":4,"writer":"李诗怡","id":26075},{"gradeId":12,"level":2,"name":"我和爸爸下象棋","time":"2014-08-20","typeId":18,"wordId":4,"writer":"胡云翔","id":29535},{"gradeId":12,"level":2,"name":"我爱家乡的小河","time":"2013-07-25","typeId":14,"wordId":4,"writer":"高豆豆","id":436003},{"gradeId":12,"level":2,"name":"整房间","time":"2014-02-20","typeId":18,"wordId":4,"writer":"李啊","id":29402},{"gradeId":12,"level":3,"name":"我那只可爱的小鸡","time":"2013-04-06","typeId":12,"wordId":6,"writer":"刘炜烨","id":19079},{"gradeId":12,"level":2,"name":"春雨作文：春雨沙沙","time":"2014-03-07","typeId":13,"wordId":4,"writer":"高豆豆","id":295609},{"gradeId":12,"level":2,"name":"飞机的秘密","time":"2013-11-04","typeId":12,"wordId":10,"writer":"高豆豆","id":18122},{"gradeId":12,"level":2,"name":"我最喜欢的鸟","time":"2014-03-26","typeId":14,"wordId":4,"writer":"嘻哈999","id":45207},{"gradeId":12,"level":2,"name":"我的好朋友","time":"2013-02-14","typeId":14,"wordId":4,"writer":"付强","id":96615},{"gradeId":12,"level":2,"name":"我的小台灯","time":"2013-08-14","typeId":14,"wordId":4,"writer":"邱琎喆","id":26548},{"gradeId":12,"level":2,"name":"秋天的向阳山","time":"2014-09-10","typeId":13,"wordId":6,"writer":"湘灵月光","id":85058},{"gradeId":12,"level":2,"name":"郊外的春天","time":"2014-03-31","typeId":13,"wordId":4,"writer":"高豆豆","id":341479},{"gradeId":12,"level":2,"name":"美丽的枫叶","time":"2014-06-24","typeId":14,"wordId":6,"writer":"高豆豆","id":488715},{"gradeId":12,"level":2,"name":"\u201c鬼\u201d混","time":"2014-12-26","typeId":12,"wordId":6,"writer":"杜宇翔","id":48391}]
         */

        private int totalCount;
        private int page;
        private int size;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * gradeId : 12
             * level : 2
             * name : 可爱的秋天
             * time : 2014-03-24
             * typeId : 13
             * wordId : 4
             * writer : 高豆豆
             * id : 343811
             */

            private int gradeId;
            private int level;
            private String name;
            private String time;
            private int typeId;
            private int wordId;
            private String writer;
            private int id;

            public int getGradeId() {
                return gradeId;
            }

            public void setGradeId(int gradeId) {
                this.gradeId = gradeId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public int getWordId() {
                return wordId;
            }

            public void setWordId(int wordId) {
                this.wordId = wordId;
            }

            public String getWriter() {
                return writer;
            }

            public void setWriter(String writer) {
                this.writer = writer;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
