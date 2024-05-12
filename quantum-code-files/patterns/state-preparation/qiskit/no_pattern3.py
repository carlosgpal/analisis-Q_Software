from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
circuito.x(0)

circuito.h(0)

circuito.x(1)
circuito.x(2)

circuito.x(3)

circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])