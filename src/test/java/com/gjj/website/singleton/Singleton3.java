package com.gjj.website.singleton;

/**
 * @author :
 * @since 2020/1/16 15:09
 */
public enum Singleton3 {
    /**
     * 由于枚举没有构造方法，因此不会通过反射来实例化对象，
     */

    INSTANCE {
        @Override
        protected void doSomething() {
            System.out.println("doSomething");
        }
    };


    protected abstract void doSomething();
}
