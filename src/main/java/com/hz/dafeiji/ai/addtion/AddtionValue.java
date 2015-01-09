package com.hz.dafeiji.ai.addtion;

/**
 * user         LIUKUN
 * time         2015-1-9 15:54
 */

public class AddtionValue{

    public AddtionType getAddtionItem(){
        return addtionItem;
    }

    private final AddtionType addtionItem;
    /**
     * 加成百分比
     */
    private float addtionPercent;

    /**
     * 加成固定值
     */
    private int addtionNum;

    public AddtionValue( AddtionType addtionItem ){
        this.addtionItem = addtionItem;
    }

    public int getAddtionNum(){
        return addtionNum;
    }

    public float getAddtionPercent(){
        return addtionPercent;
    }


    /**
     * 获取实际的加成数值
     * @return
     */
    public float getAddtionValue(){
        if( addtionNum == 0 ) {
            return addtionPercent;
        }
        return addtionNum;
    }

    public void setAddtionPercent( float percent ){
        this.addtionPercent = percent;
    }

    public void setAddtionNum( int num ){
        this.addtionNum = num;
    }

    public void plusAddtionPercent( float percent ){
        this.addtionPercent += percent;
    }

    public void plusAddtionNum( int num ){
        this.addtionNum += num;
    }

    public void plus( AddtionValue av ){

        if( addtionNum != 0 ) {
            addtionNum += av.addtionNum;
        } else {
            addtionPercent += av.addtionPercent;
        }
    }

    @Override
    public String toString(){
        return "AddtionValue{" +
                //"addtionItem=" + addtionItem +
                ", addtionPercent=" + addtionPercent +
                ", addtionNum=" + addtionNum +
                '}';
    }
}
