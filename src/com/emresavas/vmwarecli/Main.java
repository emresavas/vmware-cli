package com.emresavas.vmwarecli;

import com.vmware.vim25.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("kullanim: vmware-cli <url> <user> <pass> [komut]");
            System.out.println("ornek:    vmware-cli https://vcenter/sdk root '*****' list");
            return;
        }
        String url = args[0];
        String user = args[1];
        String pass = args[2];

        // basliyoruz - bir sonraki commit'te VimClient'a tasinacak
        System.out.println("connect: " + url);
        // todo: ServiceInstance kullanmak icin VI Java API'sini calistirmak lazim.
    }
}
