package com.emresavas.vmwarecli;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class VmPower {

    public static void on(VimClient c, String name) throws Exception {
        VirtualMachine vm = find(c, name);
        if (vm == null) { System.out.println("vm bulunamadi"); return; }
        Task t = vm.powerOnVM_Task(null);
        System.out.println("power on: " + name);
        t.waitForTask();
    }

    public static void off(VimClient c, String name) throws Exception {
        VirtualMachine vm = find(c, name);
        if (vm == null) { System.out.println("vm bulunamadi"); return; }
        Task t = vm.powerOffVM_Task();
        System.out.println("power off: " + name);
        t.waitForTask();
    }

    public static void reset(VimClient c, String name) throws Exception {
        VirtualMachine vm = find(c, name);
        if (vm == null) { System.out.println("vm bulunamadi"); return; }
        Task t = vm.resetVM_Task();
        System.out.println("reset: " + name);
        t.waitForTask();
    }

    private static VirtualMachine find(VimClient c, String name) throws Exception {
        return (VirtualMachine) new InventoryNavigator(c.rootFolder())
            .searchManagedEntity("VirtualMachine", name);
    }
}
