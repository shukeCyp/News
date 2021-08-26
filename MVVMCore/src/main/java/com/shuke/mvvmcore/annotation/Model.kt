package com.shuke.mvvmcore.annotation

/**
 *   @Author:YaPeng
 *   @Date:2021/8/17
 *   @Email:3536815334@qq.com
 *
 *   Model 注解  作用于字段，运行时有效
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Model()
