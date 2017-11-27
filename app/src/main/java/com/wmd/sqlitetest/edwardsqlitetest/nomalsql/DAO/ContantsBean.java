package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO;

/**
 * 时间：2017/10/25/14：48
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：联系人类(设计模式--builder)
 * 声明：版权归作者所有
 */

public class ContantsBean {
    public ContantsBean() {
    }

    public ContantsBean(int contantId, String contantName, int age) {
        this.contantId = contantId;
        this.contantName = contantName;
        this.age = age;
    }

    private ContantsBean(Builder builder) {
        this.contantName = builder.contantName;
        this.age = builder.age;
        this.contantId = builder.contantId;
    }

    private String contantName;
    private int age;
    private int contantId;

    public int getContantId() {
        return contantId;
    }

    public void setContantId(int contantId) {
        this.contantId = contantId;
    }

    public String getContantName() {
        return contantName;
    }

    public void setContantName(String contantName) {
        this.contantName = contantName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static class Builder {
        private String contantName;
        private int age;
        private int contantId;

        public Builder name(String name) {
            this.contantName = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder id(int id) {
            this.contantId = id;
            return this;
        }

        public ContantsBean build() {
            return new ContantsBean(this);
        }

    }

}
