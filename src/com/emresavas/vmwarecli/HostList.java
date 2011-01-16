package com.emresavas.vmwarecli;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class HostList {

    public static void run(VimClient c) throws Exception {
        ManagedEntity[] hosts = new InventoryNavigator(c.rootFolder())
            .searchManagedEntities("HostSystem");

        if (hosts == null || hosts.length == 0) {
            System.out.println("host yok.");
            return;
        }

        System.out.printf("%-30s %-12s %-8s %-8s %s%n",
            "NAME", "STATE", "CPU", "MEM(GB)", "VERSION");

        for (ManagedEntity e : hosts) {
            HostSystem h = (HostSystem) e;
            HostSummary s = h.getSummary();
            HostHardwareSummary hw = s.getHardware();
            long memGb = hw.getMemorySize() / 1024 / 1024 / 1024;
            int cpuCount = hw.getNumCpuCores();
            String version = s.getConfig() != null && s.getConfig().getProduct() != null
                ? s.getConfig().getProduct().getFullName() : "";
            System.out.printf("%-30s %-12s %-8d %-8d %s%n",
                h.getName(),
                s.getRuntime().getPowerState().toString(),
                cpuCount,
                memGb,
                version);
        }
    }
}
