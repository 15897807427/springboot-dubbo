package com.xy.configration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @description: 动态数据源
 * @author: xuyuan
 * @create: 2018-11-20 22:01
 **/
public class DynamicDataSource extends AbstractRoutingDataSource{

    private static final ThreadLocal<DataSourceType> contextHolder = new InheritableThreadLocal();

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static void master(){
        contextHolder.set(DataSourceType.Master);
    }


    public static void slave(){
        contextHolder.set(DataSourceType.Slave);
    }

    public static void setDataSourceType(DataSourceType type) {
        contextHolder.set(type);
    }

    public static DataSourceType getDataSourceType(){
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}
