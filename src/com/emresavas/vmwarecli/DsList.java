package com.emresavas.vmwarecli;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class DsList {

    public static void run(VimClient c) throws Exception {
        ManagedEntity[] ds = new InventoryNavigator(c.rootFolder())
            .searchManagedEntities("Datastore");

        if (ds == null || ds.length == 0) {
            System.out.println("datastore yok.");
            return;
        }

        System.out.printf("%-30s %-8s %12s %12s %12s%n",
            "NAME", "TYPE", "CAPACITY(GB)", "FREE(GB)", "USED(%)");

        for (ManagedEntity e : ds) {
            Datastore d = (Datastore) e;
            DatastoreSummary s = d.getSummary();
            long cap = s.getCapacity();
            long free = s.getFreeSpace();
            long used = cap - free;
            int pct = cap > 0 ? (int) ((used * 100) / cap) : 0;
            System.out.printf("%-30s %-8s %12d %12d %12d%n",
                s.getName(),
                s.getType(),
                cap / 1024 / 1024 / 1024,
                free / 1024 / 1024 / 1024,
                pct);
        }
    }
}
