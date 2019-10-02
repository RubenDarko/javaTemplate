package com.etaluma.scopesmart.util;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

public class Proxy {

    private static volatile BrowserMobProxy browserMobProxyServerInstance;

    public static BrowserMobProxy getBrowserMobProxyServer() {
        if (browserMobProxyServerInstance == null) {
            synchronized(BrowserMobProxy.class) {
                if (browserMobProxyServerInstance == null) {
                    browserMobProxyServerInstance = new BrowserMobProxyServer();
                    browserMobProxyServerInstance.start(0);
                }
            }
        }

        return browserMobProxyServerInstance;
    }
}
