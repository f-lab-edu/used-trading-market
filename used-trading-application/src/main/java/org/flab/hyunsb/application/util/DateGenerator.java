package org.flab.hyunsb.application.util;

import java.util.Date;

public interface DateGenerator {

    Date getCurrentDate();

    Date getExpireDate(long exp);
}
