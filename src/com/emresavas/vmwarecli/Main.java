package com.emresavas.vmwarecli;

public class Main {

    public static void main(String[] args) {
        if (args.length < 4) {
            usage();
            return;
        }
        String url = args[0];
        String user = args[1];
        String pass = args[2];
        String cmd  = args[3];

        VimClient c = null;
        try {
            c = new VimClient(url, user, pass);
            System.out.println("connected: " + url);
            System.out.println("about:     " + c.si().getAboutInfo().getFullName());
            // komutlar bir sonraki commit'te
            if ("list".equals(cmd)) {
                VmList.run(c);
            } else if ("ds".equals(cmd)) {
                DsList.run(c);
            } else if ("on".equals(cmd)) {
                if (args.length < 5) { System.out.println("kullanim: on <name>"); return; }
                VmPower.on(c, args[4]);
            } else if ("off".equals(cmd)) {
                if (args.length < 5) { System.out.println("kullanim: off <name>"); return; }
                VmPower.off(c, args[4]);
            } else if ("reset".equals(cmd)) {
                if (args.length < 5) { System.out.println("kullanim: reset <name>"); return; }
                VmPower.reset(c, args[4]);
            } else if ("info".equals(cmd)) {
                if (args.length < 5) {
                    System.out.println("kullanim: info <name>");
                    return;
                }
                VmInfo.run(c, args[4]);
            } else if (!"ping".equals(cmd)) {
                System.out.println("bilinmeyen komut: " + cmd);
            }
        } catch (Exception e) {
            System.err.println("hata: " + e.getMessage());
            System.exit(1);
        } finally {
            if (c != null) c.disconnect();
        }
    }

    static void usage() {
        System.out.println("kullanim: vmware-cli <url> <user> <pass> <komut>");
        System.out.println();
        System.out.println("komutlar:");
        System.out.println("  ping
  list
  info <name>
  on <name>
  off <name>
  reset <name>
  ds");
    }
}
