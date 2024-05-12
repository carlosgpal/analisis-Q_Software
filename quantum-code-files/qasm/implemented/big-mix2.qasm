OPENQASM 2.0;
include "qelib1.inc";

qreg q[4];
creg c[4];

h q[0];
x q[1];
x q[2];
x q[3];
cx q[0],q[1];
x q[3];

measure q[0]->c[0];
measure q[2]->c[1];
measure q[3]->c[2];
measure q[1]->c[3];

h q[0];
cx q[0],q[1];
x q[3];
h q[1];
cx q[0],q[1];
cx q[1],q[3];
x q[2];

measure q[0]->c[2];
measure q[2]->c[1];
measure q[3]->c[3];
measure q[1]->c[0];

h q[0];
cx q[0],q[1];
x q[2];
cx q[0],q[1];
cx q[2],q[0];

measure q[0]->c[0];
measure q[2]->c[3];
measure q[3]->c[1];
measure q[1]->c[2];