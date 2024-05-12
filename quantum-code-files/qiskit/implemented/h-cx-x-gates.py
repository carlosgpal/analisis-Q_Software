from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
circuito.h(0)
circuito.cx(0, 1)
circuito.x(3)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])