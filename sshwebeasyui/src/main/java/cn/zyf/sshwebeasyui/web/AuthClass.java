package cn.zyf.sshwebeasyui.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //指定定义的注解 @AuthClass 的生命周期
public @interface AuthClass {

	public String value() default "";
}
