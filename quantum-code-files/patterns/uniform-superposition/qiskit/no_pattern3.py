from qiskit import QuantumCircuit

circuito = QuantumCircuit(4, 4)
circuito.h(0)
circuito.x(0)
circuito.h(1)

circuito.h(2)

circuito.h(3)
circuito.measure([0, 2, 3, 1], [0, 1, 2, 3])