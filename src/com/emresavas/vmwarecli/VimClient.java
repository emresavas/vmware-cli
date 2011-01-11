package com.emresavas.vmwarecli;

import java.net.URL;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

/**
 * VI Java API wrapper. ServiceInstance ile vcenter / esx host'a baglanip
 * temel objelere erisim sagliyor.
 */
public class VimClient {

    private final ServiceInstance si;

    public VimClient(String url, String user, String pass) throws Exception {
        // self-signed cert ile vcenter geliyor - 3. parametre true = ignore cert
        this.si = new ServiceInstance(new URL(url), user, pass, true);
    }

    public ServiceInstance si() {
        return si;
    }

    public Folder rootFolder() {
        return si.getRootFolder();
    }

    public void disconnect() {
        try {
            si.getServerConnection().logout();
        } catch (Exception ignored) {
        }
    }
}
