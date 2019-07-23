package me.stefan.tech;

import android.content.Context;

/**
 * Helper
 * <p>
 * Created by Stefan on 2019/7/23.
 */
public class Helper {

    private static float scale;

    public static int dpToPixel(float dp, Context context) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }

}
