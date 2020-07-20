package com.common.configuration;

public class BeanInfo{
    enum BType {
        ID(1),
        CLASS(2),
        VALUE(3),
        REF(4);

        private final int value;

        private BType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private String beanName;
    private BType type;
    private String propertyName;
    private String propertyValue;

    public BeanInfo() {}

    public BeanInfo(String beanName, BType type, String propertyName, String propertyValue) {
        this.beanName = beanName;
        this.type = type;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "BeanInfo{" +
                "beanName='" + beanName + '\'' +
                ", type=" + type +
                ", propertyName='" + propertyName + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                '}';
    }

    public String getBeanName() {
        return beanName;
    }

    public BType getType() {
        return type;
    }

    public int getTypeByInt() {
        return type.getValue();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyValue() {return propertyValue;};

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setType(BType type) {
        this.type = type;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
