from qiskit import QuantumCircuit

circuito = QuantumCircuit(3, 3)
circuito.h(0)



circuito.cx(0, 1)
circuito.measure([0, 2, 1], [0, 1, 2])