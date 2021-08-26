package com.shuke.mvvmcore

import android.os.Build
import com.shuke.mvvmcore.annotation.Model

/**
 *   @Author:YaPeng
 *   @Date:2021/8/17
 *   @Email:3536815334@qq.com
 *   仓库层->利用注解+反射实现一个Repository对应多个Model
 */
abstract class BaseRepository {

    init {
        injectModel()
    }

    /**
     * 创建并初始化Model
     */
    fun injectModel(){
        //获取当前类型
        val javaClass = this.javaClass
        //获取到当前类所有字段
        val declaredFields = javaClass.declaredFields
        //判断是否有字段
        if (declaredFields.size > 0){
            //设置标签判断当前类中是否有Model
            var hasModel:Boolean = false
            //循环遍历所有字段，获取Model并且初始化
            for (field in declaredFields){
                //判断当前字段是否有Model注解
                val annotation = field.getAnnotation(Model::class.java)
                if (annotation == null){
                    continue
                }
                //打开可访问性
                field.isAccessible = true
                //设置类型名称，下面通过全类型名获取当前类型
                var name : String = ""
                //版本适配，获取字段类型名称
                if (Build.VERSION.SDK_INT >= 28){
                    name = field.genericType.typeName
                }
                else{
                    name = field.type.name
                }
                //通过全类型名称获取当前类型
                val type = Class.forName(name)
                //通过类型获取实例
                val newInstance = type.newInstance()
                //设置
                field.set(this,newInstance)
            }
        }
        else{
            //没有字段抛出异常
            throw IllegalStateException("当前Repository中没有字段")
        }
    }

}