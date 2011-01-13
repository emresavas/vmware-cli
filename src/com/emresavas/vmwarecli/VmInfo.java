package com.emresavas.vmwarecli;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class VmInfo {

    public static void run(VimClient c, String name) throws Exception {
        VirtualMachine vm = (VirtualMachine) new InventoryNavigator(c.rootFolder())
            .searchManagedEntity("VirtualMachine", name);

        if (vm == null) {
            System.out.println("vm bulunamadi: " + name);
            return;
        }

        VirtualMachineConfigInfo cfg = vm.getConfig();
        VirtualMachineRuntimeInfo rt = vm.getRuntime();
        VirtualMachineSummary sum = vm.getSummary();

        System.out.println("Name:        " + cfg.getName());
        System.out.println("UUID:        " + cfg.getUuid());
        System.out.println("State:       " + rt.getPowerState());
        System.out.println("CPU:         " + cfg.getHardware().getNumCPU());
        System.out.println("Memory:      " + cfg.getHardware().getMemoryMB() + " MB");
        System.out.println("Guest:       " + cfg.getGuestFullName());
        System.out.println("VM Tools:    " + (sum.getGuest() != null ? sum.getGuest().getToolsStatus() : "n/a"));
        if (sum.getGuest() != null && sum.getGuest().getIpAddress() != null) {
            System.out.println("IP:          " + sum.getGuest().getIpAddress());
        }
        System.out.println("Host:        " + (rt.getHost() != null ? rt.getHost().getValue() : "n/a"));
    }
}
