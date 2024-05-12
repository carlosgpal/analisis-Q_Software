OPENQASM 2.0;
include "qelib1.inc";

qreg q[4];
creg c[4];

rx(pi/2) q[0];
ry(pi/4) q[1];
rz(pi/6) q[2];
u3(pi/2, pi/4, pi/6) q[3];
u2(pi/4, pi/6) q[0];

measure q[0]->c[0];
measure q[2]->c[1];
measure q[3]->c[2];
measure q[1]->c[3];