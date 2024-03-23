package org.flab.hyunsb.application.service.actortoken.mock;

import java.util.Date;
import org.flab.hyunsb.application.util.DateGenerator;

public class MockDateGenerator implements DateGenerator {

    private Date currentDate;

    public MockDateGenerator() {
        currentDate = new Date(System.currentTimeMillis());
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public Date getExpireDate(long exp) {
        return new Date(currentDate.getTime() + exp);
    }

    public void setCurrentDate(Date date) {
        currentDate = date;
    }
}
