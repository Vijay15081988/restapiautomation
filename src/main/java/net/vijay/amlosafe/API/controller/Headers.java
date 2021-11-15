package net.vijay.amlosafe.API.controller;

import java.util.logging.Logger;
import net.vijay.amlosafe.API.request.model.HeadersPOJO;

public class Headers {

    net.vijay.amlosafe.API.request.model.HeadersPOJO headers;
    public static Logger log = Logger.getLogger(Headers.class.getName());

    public net.vijay.amlosafe.API.request.model.HeadersPOJO setValidHeaders() {

        log.info("=============================Setting up Header============================================");
        headers = new HeadersPOJO();
        headers.setCookie("visid_incap_1982663=kshC6CFdR0WhkbITlMY8f1d8kmEAAAAAQUIPAAAAAACWv73BJbWIoP+Da+MH//2+; nlbi_1982663=RPrCQ8QOElsUAtVO+WRZQgAAAABp2PZO51IGD3og1VzHcFO7; incap_ses_1118_1982663=6IGfGJgYSk/Izp3The+DD1d8kmEAAAAAl78VvfugAK6LIoD7mlU+/Q==; language=en; currency=SAR; WZRK_G=d9ae7523fb0446c9aa21bf0956d1d29e; WZRK_S_K76-65Z-675Z={\"p\":1,\"s\":1636990044,\"t\":1636990044}; _gcl_au=1.1.451150470.1636990051; _gid=GA1.2.68697019.1636990051; _scid=e373648a-6a28-4ecc-be7d-24eea34a1b85; _ga_LJF2ZPHK2H=GS1.1.1636990050.1.0.1636990050.60; _gaexp=GAX1.2.yUa8ziDiRUm2CwdsY3IcIQ.18970.0!7TMG60ciS3ezgXiYCDiagg.18997.0!TSkRHsKhQJ6rDt3o8OnhBQ.18999.1!oZQSew8AQQOSJYMXc3J4UA.19006.1; _ga=GA1.2.1474709822.1636990051; TEAL=v:517d2435e76854500261161230285597242296a37d0$t:1636991852204$s:1636990052202;exp-sess$sn:1$en:1; _uetsid=8dce30b0462811ec810b834e0ed93123; _uetvid=8dce4820462811ecbf39090a64f58e85; _fbp=fb.1.1636990054224.1219111378; QuantumMetricSessionID=dee361a03fa427a02c6486a3ada099ec; QuantumMetricUserID=4e8f57d4777eede8ce88e817a0e8248b; _sctr=1|1636914600000; ins-current-currency=\"SAR\"; ins-gaSSId=2e68f62a-5e6a-f950-a604-71ccdc19aaa8_1636990055; ins-storage-version=1; total-cart-amount=0; WZRK_S_R76-65Z-675Z={\"p\":1,\"s\":1636990051,\"t\":1636990173}");
        log.info("=============================Setting Done Header============================================");
        return headers;
    }


}
