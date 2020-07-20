package com.common.configuration;

import java.util.Comparator;

public class BeanInfoComparator implements Comparator<BeanInfo> {
    @Override
    public int compare(BeanInfo o1, BeanInfo o2) {
        return o1.getTypeByInt() - o2.getTypeByInt();
    }
}