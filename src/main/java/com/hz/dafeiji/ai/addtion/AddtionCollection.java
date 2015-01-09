package com.hz.dafeiji.ai.addtion;

import java.util.EnumMap;

/**
 * user         LIUKUN
 * time         2015-1-9 11:23
 * 加成容器
 */
public class AddtionCollection{

    public EnumMap<AddtionType, AddtionValue> getAddtions(){
        return addtions;
    }

    private EnumMap<AddtionType, AddtionValue> addtions = new EnumMap<AddtionType, AddtionValue>( AddtionType.class );

    public void add( AddtionValue value ){
        if( addtions.containsKey(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        value.getAddtionItem() ) ){
            addtions.get( value.getAddtionItem() ).plus( value );
        }else {
            addtions.put( value.getAddtionItem(), value );
        }
    }

    @Override
    public String toString(){
        return "Addtion{" +
                "addtions=" + addtions +
                '}';
    }

    public void add( AddtionCollection addtion1 ){
        for( AddtionValue value : addtion1.getAddtions().values() ) {
            add( value );
        }
    }
}
