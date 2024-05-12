from qiskit import QuantumCircuit
from math import pi

circuito = QuantumCircuit(4, 4)
circuito.rx(pi/2, 0)
circuito.ry(pi/4, 1)
circuito.rz(pi/6, 2)
circuito.u(pi/2, pi/4, pi/6, 3)
circuito.u2(pi/4, pi/6, 0)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])