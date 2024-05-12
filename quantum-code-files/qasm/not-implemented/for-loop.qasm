OPENQASM 2.0;
include "qelib1.inc";

qreg q[4];
creg c[4];

for int i = 0; i < 3; i = i + 1 {
    h q[i];
}