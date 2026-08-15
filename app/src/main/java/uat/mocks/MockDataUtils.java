package uat.mocks;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

import uat.models.XwyyOutput;

public class MockDataUtils {
    public static XwyyOutput createMockXwyyOutput() {
        XwyyOutput mock = new XwyyOutput();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // Date format commonly used for ProductTime and ReadTime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date(System.currentTimeMillis()));

        // Fill data matching your exact types
        mock.setId(BigDecimal.valueOf(random.nextLong(1000, 9999)));
        mock.setLineNo("LINE_" + random.nextInt(1, 10));
        mock.setRecipeName("Recipe_" + (char) (random.nextInt(65, 90)) + random.nextInt(10, 99)); // e.g., Recipe_A23
        mock.setRecipeVersion("v" + random.nextInt(1, 5) + "." + random.nextInt(0, 9));
        mock.setQty(BigDecimal.valueOf(random.nextDouble(10.0, 500.0)).setScale(2, java.math.RoundingMode.HALF_UP));
        mock.setProductTime(currentTime);
        mock.setIsRead(BigDecimal.valueOf(random.nextInt(0, 2))); // 0 or 1
        mock.setReadTime(currentTime);
        mock.setOrderNo("ORD" + random.nextLong(100000, 999999));
        mock.setSublotNo("LOT-" + random.nextInt(100, 999));

        return mock;
    }
}
