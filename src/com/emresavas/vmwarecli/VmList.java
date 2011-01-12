package com.emresavas.vmwarecli;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class VmList {

    public static void run(VimClient c) throws Exception {
        ManagedEntity[] vms = new InventoryNavigator(c.rootFolder())
            .searchManagedEntities("VirtualMachine");

        if (vms == null || vms.length == 0) {
            System.out.println("vm yok.");
            return;
        }

        System.out.printf("%-30s %-10s %-6s %-8s %s%n",
            "NAME", "STATE", "CPU", "MEM(MB)", "GUEST");

        for (ManagedEntity e : vms) {
            VirtualMachine vm = (VirtualMachine) e;
            VirtualMachineConfigInfo cfg = vm.getConfig();
            if (cfg == null) continue;
            VirtualMachineRuntimeInfo rt = vm.getRuntime();

            System.out.printf("%-30s %-10s %-6d %-8d %s%n",
                cfg.getName(),
                rt.getPowerState().toString().replace("powered", ""),
                cfg.getHardware().getNumCPU(),
                cfg.getHardware().getMemoryMB(),
                cfg.getGuestFullName() != null ? cfg.getGuestFullName() : "");
        }
    }
}
