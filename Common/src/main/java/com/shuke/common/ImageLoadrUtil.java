package com.shuke.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * @CreateDate: 2021/8/23 20:04
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: utis
 * @ClassName: ImageLoadrUtil
 */
public class ImageLoadrUtil {
    private static volatile ImageLoadrUtil imageLoadrUtil;
    public synchronized static ImageLoadrUtil getInstance() {
        if (imageLoadrUtil==null){
            synchronized (ImageLoadrUtil.class){
                if (imageLoadrUtil==null){
                    imageLoadrUtil = new ImageLoadrUtil();
                }
            }
        }
        return imageLoadrUtil;
    }

    /**
     * 加载圆形图片
     * @param context
     * @param uri
     * @param imageView
     */
    private void ImageCrop(Context context, String uri, ImageView imageView){
        Glide.with(context).load(uri).circleCrop().into(imageView);
    }

    /**
     * 加载错误图片
     * @param context
     * @param uri
     * @param imageView
     */
    private void ImageErrorCrop(Context context, String uri, ImageView imageView){
        Glide.with(context).load(uri).error(R.drawable.delete).into(imageView);
    }

}
