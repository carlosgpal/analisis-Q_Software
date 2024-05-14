from qiskit import QuantumCircuit

circuito = QuantumCircuit(3, 3)
circuito.h(0)



circuito.cx(1, 0)
circuito.measure([0, 2, 1], [0, 1, 2])