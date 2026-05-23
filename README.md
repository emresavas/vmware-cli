# vmware-cli

> Old repo. Built while I was figuring out cloud topologies like IaaS, PaaS, virtualization and networking. There may be implementation mistakes. Not actively maintained.

VMware vSphere / ESX(i) icin kucuk bir Java CLI. vim25 SDK uzerinde
calisiyor (vSphere 4.1 ile test edildi, 5.0 da uyumlu olmali).

## komutlar

```
vmware-cli <url> <user> <pass> ping
vmware-cli <url> <user> <pass> list
vmware-cli <url> <user> <pass> info <vm-adi>
vmware-cli <url> <user> <pass> on    <vm-adi>
vmware-cli <url> <user> <pass> off   <vm-adi>
vmware-cli <url> <user> <pass> reset <vm-adi>
vmware-cli <url> <user> <pass> ds
vmware-cli <url> <user> <pass> hosts
```

## kurulum

`vim25.jar` dosyasini VMware vSphere Management SDK paketinden alip
`lib/` altina koy. Sonra:

```
ant jar
./run.sh https://<vcenter>/sdk root '****' list
```

## bagimliliklar

- Java 6
- VMware vSphere Management SDK 4.1 (vim25.jar)
- Apache Ant

## not

baglandiktan sonra `searchManagedEntities("VirtualMachine")` ile tum vm'lere
ulasiyoruz. cluster / vapp gibi hiyerarsileri henuz ele almiyor.
