package com.hz.dafeiji.ai.addtion;

import org.junit.Test;

public class AddtionTest{

    @Test
    public void testAdd() throws Exception{

        AddtionValue av = new AddtionValue( AddtionType.ATTACK_ADDTION );
        av.setAddtionNum( 100 );
        av.plusAddtionNum( 65 );

        AddtionValue av1 = new AddtionValue( AddtionType.CASH_ADDTION);
        av1.setAddtionPercent( 0.2f );
        av1.plusAddtionPercent( 0.1f );


        AddtionCollection addtion = new AddtionCollection();
        addtion.add( av );
        addtion.add( av1 );

        AddtionCollection addtion1 = new AddtionCollection();
        addtion1.add( av );
        addtion1.add( av1 );

        System.out.println( addtion );
        addtion.add( addtion1 );
        System.out.println( addtion );



    }
}