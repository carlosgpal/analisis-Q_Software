from qiskit import QuantumCircuit
from math import pi

circuito = QuantumCircuit(4, 4)
circuito.cz(0, 1)
circuito.cp(pi/2, 1, 2)
circuito.crz(pi/4, 2, 3)
circuito.ccx(0, 1, 2)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])