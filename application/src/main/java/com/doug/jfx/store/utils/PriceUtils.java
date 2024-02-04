package com.doug.jfx.store.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.function.Function;

public class PriceUtils {

    private static final String PRICE_BR = "R$ #,##0.00";
    private static final DecimalFormat moneyFormat = new DecimalFormat(PRICE_BR);

    public static String pricePtBr(BigDecimal price) {
        return moneyFormat.format(price.doubleValue());
    }

}
