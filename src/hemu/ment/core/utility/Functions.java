package hemu.ment.core.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by muu on 2015/5/28.
 */
public final class Functions {

    private Functions() {}

    public static int[] intArray(int begin, int size) {
        int[] array = new int[size];
        for (int i = begin, j = 0; j < size; i++, j++) {
            array[j] = i;
        }
        return array;
    }

    public static String dateFormat(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }


}
